package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.NacinPlacanja;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.KorisnikRepo;
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

    @Override
    public void execute(DelegateExecution execution){

        String ime = (String) execution.getVariable("ime");

        Boolean bank = (Boolean) execution.getVariable("bank");
        Boolean paypal = (Boolean) execution.getVariable("paypal");
        Boolean btc = (Boolean) execution.getVariable("btc");

        String urednik = (String) execution.getVariable("ime_urednika");

        ArrayList<NacinPlacanja> naciniPlacanja = new ArrayList<>();

        Long issn = (Long) execution.getVariable("issn");
        Boolean clanarina = (Boolean) execution.getVariable("clanarina");
        ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");

        Casopis c = new Casopis();

        c.setIme(ime);


        if(bank){
            NacinPlacanja np = new NacinPlacanja();
            np.setIme("bank");
            np.setSifra(new Long(1));
            naciniPlacanja.add(np);
        }

        if(paypal){
            NacinPlacanja np = new NacinPlacanja();
            np.setIme("paypal");
            np.setSifra(new Long(2));
            naciniPlacanja.add(np);
        }

        if(btc){
            NacinPlacanja np = new NacinPlacanja();
            np.setIme("btc");
            np.setSifra(new Long(3));
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






    }
}
