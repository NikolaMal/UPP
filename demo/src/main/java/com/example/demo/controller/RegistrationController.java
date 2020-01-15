package com.example.demo.controller;

import com.example.demo.model.FormFieldsDTO;
import com.example.demo.model.FormSubmissionDTO;
import com.example.demo.model.TaskDTO;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.webapp.impl.security.auth.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RuntimeService runtimeService;

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
    public @ResponseBody ResponseEntity<List<TaskDTO>> get(@PathVariable String assignee){
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        List<TaskDTO> response = new ArrayList<TaskDTO>();

//        List<Task> admin_taskovi = taskService.createTaskQuery().taskAssignee("demo").list();

//        for(Task task : admin_taskovi){
//            tasks.add(task);
//        }

        for(Task task : tasks){
            TaskDTO temp = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
            response.add(temp);
        }

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/getFields/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<FormField>> getFields(@PathVariable String taskId){
        List<FormField> fields = formService.getTaskFormData(taskId).getFormFields();


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


}
