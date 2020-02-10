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

        Korisnik k = new Korisnik();
        k.setIme((String) execution.getVariable("koautor_ime"));
        k.setEmail((String) execution.getVariable("koautor_email"));
        k.setGrad((String) execution.getVariable("koautor_grad"));
        k.setDrzava((String) execution.getVariable("koautor_drzava"));
        k.setUsername(k.getIme());

        koautori.add(k);

        korisnikRepo.save(k);

        execution.setVariable("koautori", koautori);
    }
}
