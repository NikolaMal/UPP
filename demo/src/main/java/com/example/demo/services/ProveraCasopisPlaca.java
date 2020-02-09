package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.repo.CasopisRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveraCasopisPlaca implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    public void execute(DelegateExecution execution){
        String ime_casopisa = (String) execution.getVariable("casopis");
        Casopis c = casopisRepo.findOneByIme(ime_casopisa);

        System.out.println("ime casopisa " + ime_casopisa);
        System.out.println(c);

        if(c == null || c.getClanarina() == null || !c.getClanarina()){
            execution.setVariable("placa_se", false);
        }

        else {
            execution.setVariable("placa_se", true);
        }

        List<Korisnik> koautori = new ArrayList<>();

        execution.setVariable("koautori", koautori);


    }
}
