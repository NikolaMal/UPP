package com.example.demo.services;

import com.example.demo.model.Korisnik;
import com.example.demo.repo.KorisnikRepo;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class ObavestiRecenzente implements JavaDelegate {

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
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

        for(String username : (ArrayList<String>) execution.getVariable("lista_recenzenata")){
            Korisnik k = korisnikRepo.findOneByUsername(username);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nmalencic@gmail.com", false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nmalencic@gmail.com"));
            msg.setSubject("Stigao novi zadatak recenziranja za " + k.getUsername());
            msg.setContent("Stigao novi zadatak recenziranja za " + k.getUsername(), "text/html");
            msg.setSentDate(new Date());

            Transport.send(msg);
        }

        List<String> lista = (ArrayList) execution.getVariable("lista_recenzenata");

        for(String s : lista) {
            System.out.println(s);
        }

        int brojac = (int) execution.getVariable("brojac");
        brojac++;
        execution.setVariable("brojac", brojac);


    }
}
