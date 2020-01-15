package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class AddCasopisProcessHandler implements ExecutionListener {

    @Autowired
    IdentityService identityService;

    @Autowired
    AuthorizationService authorizationService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        ArrayList<NaucnaOblast> oblasti = new ArrayList<NaucnaOblast>();
        ArrayList<Korisnik> urednici = new ArrayList<>();
        ArrayList<Korisnik> recenzenti = new ArrayList<>();

        execution.setVariable("oblasti", oblasti);
        execution.setVariable("urednici", urednici);
        execution.setVariable("recenzenti", recenzenti);

    }
}
