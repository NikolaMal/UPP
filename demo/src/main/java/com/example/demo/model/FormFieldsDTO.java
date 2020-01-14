package com.example.demo.model;

import org.camunda.bpm.engine.form.FormField;

import java.util.List;

public class FormFieldsDTO {

    String TaskID;
    List<FormField> formFields;
    String processInstanceID;

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }

    public String getProcessInstanceID() {
        return processInstanceID;
    }

    public void setProcessInstanceID(String processInstanceID) {
        this.processInstanceID = processInstanceID;
    }

    public FormFieldsDTO(String taskID, List<FormField> formFields, String processInstanceID) {
        TaskID = taskID;
        this.formFields = formFields;
        this.processInstanceID = processInstanceID;
    }
}
