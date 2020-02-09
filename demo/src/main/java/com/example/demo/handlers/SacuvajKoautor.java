package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SacuvajKoautor implements TaskListener {

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void notify(DelegateTask delegateTask) {
        DelegateExecution execution = delegateTask.getExecution();

        List<Korisnik> koautori = (ArrayList) execution.getVariable("koautori");

        koautori.add(korisnikRepo.findOneByUsername((String) execution.getVariable("koautor")));

        execution.setVariable("koautori", koautori);
    }
}
