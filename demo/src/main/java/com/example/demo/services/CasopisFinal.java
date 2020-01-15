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
import java.util.List;

@Service
public class CasopisFinal implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void execute(DelegateExecution execution){

        System.out.println("USAO U CASOPIS FINAL");

        Long stari_issn = (Long) execution.getVariable("stari_issn");

        System.out.println("stari issn u finalu" + stari_issn);

        Casopis casopis = casopisRepo.findOneByIssn(stari_issn);

        System.out.println(casopis.getId());
        System.out.println(casopis.getIssn());

        Long novi_issn = (Long) execution.getVariable("issn");

        casopis.setAktivan(true);
        casopis.setIssn(novi_issn);
        casopis.setIme((String)execution.getVariable("ime"));


        List<Korisnik> urednici = (ArrayList)execution.getVariable("urednici");
        List<Korisnik> recenzenti =(ArrayList) execution.getVariable("recenzenti");

        casopis.setRecenzenti(recenzenti);
        casopis.setUrednici(urednici);

        casopisRepo.save(casopis);

    }
}
