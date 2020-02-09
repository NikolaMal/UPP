package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadKoautor implements TaskListener {

    @Autowired
    KorisnikRepo  korisnikRepo;
    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask delegateTask) {
        DelegateExecution execution = delegateTask.getExecution();

        List<FormField> fields = formService.getTaskFormData(delegateTask.getId()).getFormFields();

        for(FormField field : fields){
            if(field.getId().equals("koautor")){
                EnumFormType enumFormType = (EnumFormType) field.getType();

                for(Korisnik k : korisnikRepo.findAll()){
                    enumFormType.getValues().put(k.getUsername(), k.getUsername());
                }
            }
        }

    }
}
