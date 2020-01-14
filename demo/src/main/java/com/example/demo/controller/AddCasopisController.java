package com.example.demo.controller;

import com.example.demo.model.FormFieldsDTO;
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

import java.util.List;

@Controller
@RequestMapping(path = "/add")
public class AddCasopisController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    FormService formService;

    @RequestMapping(method = RequestMethod.GET, path = "/start", produces = "application/json")
    @PreAuthorize("hasAuthority('SET_UREDNIK_TASK')")
    public @ResponseBody FormFieldsDTO start() {

        System.out.println("USAO U START PROCESS DODAVANJE CASOPISA");

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("dodavanje_casopisa");

        System.out.println("ID je " + instance.getId());

        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).list().get(0);

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormField> fields = taskFormData.getFormFields();


        return new FormFieldsDTO(task.getId(), fields, instance.getId());
    }
}
