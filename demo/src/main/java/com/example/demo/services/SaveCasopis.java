package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.NacinPlacanja;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.KorisnikRepo;
import com.example.demo.repo.NacinPlacanjaRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SaveCasopis implements JavaDelegate {

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    NacinPlacanjaRepo nacinPlacanjaRepo;

    @Override
    public void execute(DelegateExecution execution){

        String ime = (String) execution.getVariable("ime");

        Boolean bank = (Boolean) execution.getVariable("bank");
        Boolean paypal = (Boolean) execution.getVariable("paypal");
        Boolean btc = (Boolean) execution.getVariable("btc");

        String urednik = (String) execution.getVariable("starter");

        ArrayList<NacinPlacanja> naciniPlacanja = new ArrayList<>();

        Long issn = (Long) execution.getVariable("issn");
        Boolean clanarina = (Boolean) execution.getVariable("clanarina");
        ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");

        Casopis c = new Casopis();

        c.setIme(ime);


        if(bank){
            NacinPlacanja np = nacinPlacanjaRepo.findOneByIme("BANK");
            naciniPlacanja.add(np);
        }

        if(paypal){
            NacinPlacanja np = nacinPlacanjaRepo.findOneByIme("PAYPAL");
            naciniPlacanja.add(np);
        }

        if(btc){
            NacinPlacanja np = nacinPlacanjaRepo.findOneByIme("BITCOIN");
            naciniPlacanja.add(np);
        }

        c.setNaciniPlacanja(naciniPlacanja);
        c.setNaucneOblasti(oblasti);
        c.setIssn(issn);
        c.setClanarina(clanarina);
        c.setGlavni_urednik(urednik);

        for(NaucnaOblast oblast : oblasti){
            System.out.println(oblast.getSifra());
        }

        for(NacinPlacanja pl : naciniPlacanja){
            System.out.println(pl.getSifra());
        }


        casopisRepo.save(c);

        System.out.println("stari issn: " + issn);

        execution.setVariable("stari_issn", issn);






    }
}
