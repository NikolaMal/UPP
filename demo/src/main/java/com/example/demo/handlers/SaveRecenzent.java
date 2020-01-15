package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
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
public class SaveRecenzent implements TaskListener {

    @Autowired
    FormService formService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void notify(DelegateTask delegateTask){
        DelegateExecution execution = delegateTask.getExecution();

        List<FormField> fields = formService.getTaskFormData(delegateTask.getId()).getFormFields();

        List<Korisnik> recenzenti = (ArrayList) execution.getVariable("recenzenti");
        List<Korisnik> urednici = (ArrayList) execution.getVariable("urednici");

        for(FormField field : fields){
            if(field.getId().equals("recenzent")){
                Korisnik rec = korisnikRepo.findOneByUsername(field.getValue().getValue().toString());
                recenzenti.add(rec);
            }
        }

        for(FormField field : fields){
            if(field.getId().equals("urednik")){
                Korisnik ur = korisnikRepo.findOneByUsername(field.getValue().getValue().toString());
                urednici.add(ur);
            }
        }

        if(!recenzenti.isEmpty()){
            execution.setVariable("recenzenti", recenzenti);
        }

        if(!urednici.isEmpty()){
            execution.setVariable("urednici", urednici);
        }
    }
}
