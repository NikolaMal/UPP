package com.example.demo.handlers;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoadKomentari implements TaskListener {

    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask delegateTask) {
        DelegateExecution execution = delegateTask.getExecution();



        List<String> komentari = (ArrayList) execution.getVariable("lista_komentara");
        List<String> komentari_autor = (ArrayList) execution.getVariable("lista_komentara_autor");

        List<FormField> fields = formService.getTaskFormData(delegateTask.getId()).getFormFields();

        for(FormField field : fields){
            if(field.getId().equals("kom")){
                EnumFormType enumFormType = (EnumFormType) field.getType();

                for(String s : komentari){
                    enumFormType.getValues().put(s, s);
                }
            }
        }

        for(FormField field : fields){
            if(field.getId().equals("komentari")){
                EnumFormType enumFormType = (EnumFormType) field.getType();

                for(String s : komentari_autor){
                    enumFormType.getValues().put(s,s);
                }
            }
        }

    }
}
