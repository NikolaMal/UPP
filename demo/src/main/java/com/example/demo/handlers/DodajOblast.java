package com.example.demo.handlers;

import com.example.demo.model.FormFieldsDTO;
import com.example.demo.model.FormSubmissionDTO;
import com.example.demo.model.NaucnaOblast;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DodajOblast implements TaskListener {

    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask delegateTask){

        System.out.println("USAO U TASK LISTENER DODAJ OBLAST");

        DelegateExecution execution = delegateTask.getExecution();

        //String oblast = (String) execution.getVariable("naziv_oblasti");

        ArrayList<FormField> fields = (ArrayList) formService.getTaskFormData(delegateTask.getId()).getFormFields();
        String oblast = null;
        for(FormField field : fields){
            if(field.getId().equals("naziv_oblasti")){
                oblast = (String) field.getValue().getValue();
            }
        }

            System.out.println(oblast);

            ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");

        NaucnaOblast nova = new NaucnaOblast();
        nova.setIme(oblast);



        switch(oblast){
            case "matematika": nova.setSifra(new Long(1));
                                break;
            case "fizika": nova.setSifra(new Long(2));
                            break;
            case "biologija":nova.setSifra(new Long(3));
                break;
            case "medicina":nova.setSifra(new Long(4));
            break;
            default: nova.setSifra(new Long(1));
        }

        oblasti.add(nova);

        execution.setVariable("oblasti", oblasti);


    }
}
