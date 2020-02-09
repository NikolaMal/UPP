package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.repo.CasopisRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class ProveraAutoroveClanarine implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    public AuthenticationManager manager;

    public void execute(DelegateExecution execution){
        String ime_casopisa = (String) execution.getVariable("casopis");
        Casopis c = casopisRepo.findOneByIme(ime_casopisa);
        String username_autora = (String) execution.getVariable("starter");

        execution.setVariable("placeno", false);

        for(Korisnik k : c.getAutori_platili()){
            System.out.println("provera autorove clanarine proverava se " + k.getUsername());
            if(k.getUsername().equals(username_autora)){
                execution.setVariable("placeno", true);
            }
        }



    }
}
