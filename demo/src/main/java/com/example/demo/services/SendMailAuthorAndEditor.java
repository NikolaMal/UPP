package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Rad;
import com.example.demo.repo.CasopisRepo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class SendMailAuthorAndEditor implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    RadRepo radRepo;

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
        msg.setSubject("Obavestenje za autora");
        msg.setContent("Prilozili ste novi rad", "text/html");
        msg.setSentDate(new Date());

//        Transport.send(msg);

        Message msg2 = new MimeMessage(session);
        msg2.setFrom(new InternetAddress("nmalencic@gmail.com", false));
        msg2.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nmalencic@gmail.com"));
        msg2.setSubject("Obavestenje za glavnog urednika");
        msg2.setContent("Stigao je novi rad za pregled", "text/html");
        msg2.setSentDate(new Date());

//        Transport.send(msg2);

        Casopis c = casopisRepo.findOneByIme((String) execution.getVariable("casopis"));

        execution.setVariable("glavni_urednik", c.getGlavni_urednik());

        List<Korisnik> koautori = (ArrayList) execution.getVariable("koautori");

        Rad r = radRepo.findOneByNaslov((String) execution.getVariable("naslov_rada"));


        r.setKoautori(koautori);

        radRepo.save(r);
    }
}
