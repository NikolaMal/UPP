package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CasopisFinal implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void execute(DelegateExecution execution){

        System.out.println("USAO U CASOPIS FINAL");

        Long issn = (Long) execution.getVariable("issn");

        Casopis casopis = casopisRepo.findOneByIssn(issn);

        casopis.setAktivan(true);
        ArrayList<Korisnik> recenzenti = new ArrayList<>();
        ArrayList<Korisnik> urednici = new ArrayList<>();

        String rec1 = (String) execution.getVariable("recenzent1");
        String rec2 = (String) execution.getVariable("recenzent2");
        String ured1 = (String) execution.getVariable("urednik1");
        String ured2 = (String) execution.getVariable("urednik2");

        Korisnik recen1 = korisnikRepo.findOneByUsername(rec1);
        Korisnik recen2 = korisnikRepo.findOneByUsername(rec2);
        Korisnik urednik1 = korisnikRepo.findOneByUsername(ured1);
        Korisnik urednik2 = korisnikRepo.findOneByUsername(ured2);

        recenzenti.add(recen1);
        recenzenti.add(recen2);
        urednici.add(urednik1);
        urednici.add(urednik2);

        casopis.setRecenzenti(recenzenti);
        casopis.setUrednici(urednici);

        casopisRepo.save(casopis);

    }
}
