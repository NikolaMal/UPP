package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.KorisnikRepo;
import com.example.demo.repo.NaucnaOblastRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class FindUrednikAndNotify implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    NaucnaOblastRepo naucnaOblastRepo;

    public void execute(DelegateExecution execution) throws Exception{

        System.out.println("Usao u findUrednikAndNotify");

        List<String> recenzenti = new ArrayList<>();
        execution.setVariable("lista_recenzenata", recenzenti);

        NaucnaOblast trazena_oblast = naucnaOblastRepo.findOneByIme((String) execution.getVariable("naucna_oblast"));
        Casopis c = casopisRepo.findOneByIme((String) execution.getVariable("casopis"));
        Korisnik konacni_urednik;

        execution.setVariable("urednik", c.getGlavni_urednik());

        System.out.println("Dosao dovde nobl: " + trazena_oblast.getIme() + " casopis: " + c.getIme() + " glavni urednik: " + execution.getVariable("glavni_urednik"));

        label : for(Korisnik urednik : c.getUrednici()){
            System.out.println("trenutni urednik: " + urednik.getUsername());
            for(NaucnaOblast nobl : urednik.getNaucneOblasti()){
                System.out.println("Trenutna oblast: " + nobl.getIme());
                if(nobl.getIme().equals(trazena_oblast.getIme())){
                    execution.setVariable("urednik", urednik.getUsername());
                    System.out.println("Urednik " + urednik.getUsername() + "postavljen za urednik");
                    konacni_urednik = urednik;
                    break label;
                }

                else {
                    System.out.println("Urednik je " + (String) execution.getVariable("glavni_urednik"));

                    konacni_urednik = korisnikRepo.findOneByUsername( (String) execution.getVariable("glavni_urednik"));
                    execution.setVariable("urednik", execution.getVariable("glavni_urednik"));
                }
            }
        }

        Properties props = new Properties();
        //String email = (String) execution.getVariable("email");
        String processInstanceId = execution.getProcessInstanceId();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nmalencic@gmail.com", "najfparti");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("nmalencic@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nmalencic@gmail.com"));
        msg.setSubject("Stigao novi rad za vasu naucnu oblast");
        msg.setContent("Stigao novi rad za vasu naucnu oblast", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);

        System.out.println("IZLAZIM IZ NOTIFY");

        execution.setVariable("brojac", 0);

        List<String> komentari = new ArrayList<>();

        execution.setVariable("lista_komentara", komentari);

        List<String> komentari_autor = new ArrayList<>();

        execution.setVariable("lista_komentara_autor", komentari_autor);


        List<Korisnik> recenzenti2 = c.getRecenzenti();
        int rec_brojac = 0;

        for(Korisnik rc : recenzenti2){
            for(NaucnaOblast nol : rc.getNaucneOblasti()){
                if(nol.equals(trazena_oblast)){
                    rec_brojac++;
                }
            }
        }

        if(rec_brojac < 2){
            execution.setVariable("urednik_recenzent", true);
        } else {
            execution.setVariable("urednik_recenzent", false);
        }

    }
}
