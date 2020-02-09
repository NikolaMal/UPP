package com.example.demo.handlers;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Role;
import com.example.demo.repo.CasopisRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoadCasopisi implements TaskListener {

    @Autowired
    FormService formService;

    @Autowired
    CasopisRepo casopisRepo;


    @Override
    public void notify(DelegateTask delegateTask) {
        DelegateExecution execution = delegateTask.getExecution();

        ArrayList<Casopis> casopisi = (ArrayList) casopisRepo.findAll();

        ArrayList<FormField> fields = (ArrayList) formService.getTaskFormData(delegateTask.getId()).getFormFields();

        for (FormField field : fields) {
            if (field.getId().equals("casopis")) {
                EnumFormType enumType = (EnumFormType) field.getType();
                for (Casopis c : casopisi) {
                    enumType.getValues().put( c.getIme(), c.getIme());
                }
            }

        }

    }

}
