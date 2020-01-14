package com.example.demo.services;


import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveraRegistracionihPodataka implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    KorisnikRepo repo;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("USAO U SERVICE TASK PROVERA PODATAKA");

        Boolean validni_podaci = true;

        String email = (String) execution.getVariable("email");
        String username = (String) execution.getVariable("username");

        ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) repo.findAll();

        for(Korisnik k : korisnici){
            if(k.getEmail().equals(email) || k.getUsername().equals(username)){
                validni_podaci = false;
                break;
            }
        }



        if(!validni_podaci){
            System.out.println("email or username already taken");
            execution.setVariable("tacnost_podataka", false);
        }

        else {

            execution.setVariable("tacnost_podataka", true);
        }

    }
}
