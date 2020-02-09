package com.example.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
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
public class ObavestiUrednikaDodatNoviPDF implements JavaDelegate {

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

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("nmalencic@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nmalencic@gmail.com"));
        msg.setSubject("Obavestenje za urednika (dodat novi pdf ili izmena rada)");
        msg.setContent("Dodat novi pdf", "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);

    }
}
