package com.example.demo.controller;

import com.example.demo.model.FormFieldsDTO;
import com.example.demo.security.TokenUtils;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/add")
public class AddCasopisController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @RequestMapping(method = RequestMethod.GET, path = "/start", produces = "application/json")
    @PreAuthorize("hasAuthority('SET_UREDNIK_TASK')")
    public @ResponseBody FormFieldsDTO start(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("starter", getUsernameFromRequest(request));


        ProcessInstance instance = runtimeService.startProcessInstanceByKey("dodavanje_casopisa", map);




        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).list().get(0);

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormField> fields = taskFormData.getFormFields();


        return new FormFieldsDTO(task.getId(), fields, instance.getId());
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
