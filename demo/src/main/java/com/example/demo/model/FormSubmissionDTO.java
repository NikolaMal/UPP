package com.example.demo.model;

import java.io.Serializable;

public class FormSubmissionDTO implements Serializable {
    String fieldID;
    String fieldValue;

    public FormSubmissionDTO(){
        super();
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public FormSubmissionDTO(String fieldID, String fieldValue) {
        super();
        this.fieldID = fieldID;
        this.fieldValue = fieldValue;
    }
}
