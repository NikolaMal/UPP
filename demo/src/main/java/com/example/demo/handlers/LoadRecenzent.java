package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoadRecenzent implements TaskListener {

    @Autowired
    FormService formService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void notify(DelegateTask delegateTask){
        DelegateExecution execution = delegateTask.getExecution();
        ArrayList<FormField> fields = (ArrayList) formService.getTaskFormData(delegateTask.getId()).getFormFields();
        ArrayList<Korisnik> recenzenti = new ArrayList<>();
        ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");



        ArrayList<Korisnik> korisnici = (ArrayList) korisnikRepo.findAll();





    }
}
