package com.example.demo.handlers;

import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.NaucnaOblastRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadOblasti implements TaskListener {

    @Autowired
    NaucnaOblastRepo naucnaOblastRepo;

    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask task){

        List<NaucnaOblast> oblasti = naucnaOblastRepo.findAll();

        List<FormField> fields = formService.getTaskFormData(task.getId()).getFormFields();

        for(FormField field : fields){
            if(field.getId().equals("naucna_oblast")){
                EnumFormType enumType = (EnumFormType) field.getType();

                for(NaucnaOblast nobl : oblasti){
                    enumType.getValues().put(nobl.getIme(), nobl.getIme());
                }
            }
        }


    }
}
