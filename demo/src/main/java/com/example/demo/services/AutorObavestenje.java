package com.example.demo.services;

import com.example.demo.model.Rad;
import com.example.demo.repo.RadRepo;
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
import java.util.Date;
import java.util.Properties;

@Service
public class AutorObavestenje implements JavaDelegate {

    @Autowired
    RadRepo radRepo;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String odluka = (String) execution.getVariable("odluka");

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

        msg.setSentDate(new Date());



        switch(odluka){
            case "prihvatiti":
                msg.setSubject("Rad Vam je prihvacen!");
                msg.setContent("Rad Vam je prihvacen!", "text/html");
                Rad r = radRepo.findOneByNaslov((String) execution.getVariable("naslov_rada"));
                r.setPrihvacen(true);
                Transport.send(msg);
                break;

            case "prihvatiti_m":
                msg.setSubject("Morate izmeniti rad (manje izmene)");
                msg.setContent("Text", "text/html");
                Transport.send(msg);
                break;

            case "prihvatiti_v":
                msg.setSubject("Morate izmeniti rad (vece izmene)");
                msg.setContent("text", "text/html");
                Transport.send(msg);
                break;
            case "odbiti":
                msg.setSubject("Rad Vam je odbijen");
                msg.setContent("text", "text/html");
                Transport.send(msg);
                break;
        }
    }
}
