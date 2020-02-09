package com.example.demo.controller;

import com.example.demo.model.FormFieldsDTO;
import com.example.demo.model.FormSubmissionDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.TaskDTO;
import com.example.demo.security.TokenUtils;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.camunda.bpm.engine.form.FormType;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.form.type.FormTypes;
import org.camunda.bpm.engine.impl.form.type.StringFormType;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.bpm.webapp.impl.security.auth.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @Autowired
    IdentityService identityService;

    // pokretanje procesa i prvi task (unos podataka)
    @RequestMapping(method = RequestMethod.GET, path = "/start", produces = "application/json")
    public @ResponseBody FormFieldsDTO get(){

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("registracija_korisnika");

        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).list().get(0);

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormField> fields = taskFormData.getFormFields();


        return new FormFieldsDTO(task.getId(), fields, instance.getId());

    }

    @RequestMapping(method = RequestMethod.GET, path = "/getTasks/{assignee}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDTO>> get(@PathVariable String assignee, HttpServletRequest request){

        List<TaskDTO> response = new ArrayList<TaskDTO>();

//        List<Task> admin_taskovi = taskService.createTaskQuery().taskAssignee("demo").list();

//        for(Task task : admin_taskovi){
//            tasks.add(task);
//        }

        String assigne = getUsernameFromRequest(request);

        if(assigne == null){
            assigne = assignee;
        }

        System.out.println("Traze se zadaci za " + assigne);

        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assigne).list();

        System.out.println(tasks);

        for(Task task : tasks){
            TaskDTO temp = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
            response.add(temp);
        }

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/getFields/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<FormField>> getFields(@PathVariable String taskId){
        List<FormField> fields = formService.getTaskFormData(taskId).getFormFields();
        List<Korisnik> recenzenti = (ArrayList) runtimeService.getVariable(taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId(), "recenzenti");
        List<Korisnik> urednici = (ArrayList) runtimeService.getVariable(taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId(), "urednici");
        String svi = new String();
        if(recenzenti != null && urednici != null){
            for(Korisnik rec : recenzenti){
                svi = svi + rec.getUsername() + ", ";
            }

            for(Korisnik ur : urednici){
                svi = svi + ur.getUsername() + ", ";
            }
        }


        final String ok = svi;


        if(taskService.createTaskQuery().taskId(taskId).singleResult().getAssignee().equals("demo") && taskService.createTaskQuery().taskId(taskId).singleResult().getName().equals("Provera podataka od strane admina")){
            FormField field = new FormField() {
                @Override
                public String getId() {
                    return "svi";
                }

                @Override
                public String getLabel() {
                    return "Svi: ";
                }

                @Override
                public FormType getType() {
                    return new StringFormType();
                }

                @Override
                public String getTypeName() {
                    return "string";
                }

                @Override
                public Object getDefaultValue() {
                    return ok;
                }

                @Override
                public TypedValue getValue() {
                    return null;
                }

                @Override
                public List<FormFieldValidationConstraint> getValidationConstraints() {
                    List<FormFieldValidationConstraint> list = new ArrayList<>();
                    FormFieldValidationConstraint constraint = new FormFieldValidationConstraint() {
                        @Override
                        public String getName() {
                            return "readonly";
                        }

                        @Override
                        public Object getConfiguration() {
                            return null;
                        }
                    };

                    list.add(constraint);
                    return list;
                }

                @Override
                public Map<String, String> getProperties() {
                    return null;
                }

                @Override
                public boolean isBusinessKey() {
                    return false;
                }
            };

            fields.add(field);
        }





        return new ResponseEntity(fields, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/submitForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity submitForm(@RequestBody List<FormSubmissionDTO> fields, @PathVariable String taskId){
        System.out.println("USAO U SUBMIT FORM " + taskId);
        HashMap<String, Object> map = remapDTO(fields);
        System.out.println(map);

        formService.submitTaskForm(taskId, map);





        return new ResponseEntity( HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/complete/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity completeTask(@PathVariable String taskId){
        System.out.println("USAO U COMPLETE TASK " + taskId);
        taskService.complete(taskId);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/validateEmail/{processId}", produces = "application/json")
    public @ResponseBody ResponseEntity<String> validateEmail(@PathVariable String processId){
        System.out.println("USAO U VALIDATE MAIL " + processId);

        runtimeService.setVariable(processId, "potvrdio", true);


        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:4200/success"));


        return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);

    }



    private HashMap<String, Object> remapDTO(List<FormSubmissionDTO> fields){
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(FormSubmissionDTO temp: fields){
            map.put(temp.getFieldID(), temp.getFieldValue());
        }

        return map;
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String authToken = tokenUtils.getToken(request);
        if (authToken == null) {
            return null;
        }
        String username = tokenUtils.getUsernameFromToken(authToken);
        return username;
    }


}
