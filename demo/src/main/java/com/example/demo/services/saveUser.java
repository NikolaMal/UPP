package com.example.demo.services;

import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.model.Recenzent;
import com.example.demo.model.Role;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class saveUser implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    KorisnikRepo repo;

    @Override
    public void execute(DelegateExecution execution) throws Exception{

        System.out.println("USAO U SAVE USER SERVICE TASK");

        String email = (String) execution.getVariable("email");
        User user = identityService.newUser(email);
        String ime = (String) execution.getVariable("ime");
        String prezime = (String) execution.getVariable("prezime");
        String password = (String) execution.getVariable("password");
        String salt = BCrypt.gensalt();
        String hashed_pw = BCrypt.hashpw(password, salt);

        user.setPassword(hashed_pw);
        user.setEmail(email);
        user.setFirstName(ime);
        user.setLastName(prezime);
        String grad = (String) execution.getVariable("grad");
        String drzava = (String) execution.getVariable("drzava");
        String titula = (String) execution.getVariable("titula");
        String username = (String) execution.getVariable("username");
        ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");

        Boolean recenzent = (Boolean) execution.getVariable("odobrena_recenzentura");

        if(recenzent){
            Recenzent k = new Recenzent();
            k.setUsername(username);
            k.setIme(ime);
            k.setPrezime(prezime);
            k.setDrzava(drzava);
            k.setGrad(grad);
            k.setEmail(email);
            k.setNaucneOblasti(oblasti);
            k.setTitula(titula);
            k.setPassword(hashed_pw);

            Role rola = new Role();
            rola.setId(new Long(3));
            rola.setName("RECENZENT");
            List<Role> lista = new ArrayList<Role>();
            lista.add(rola);
            k.setRoles(lista);

            repo.save(k);

        }

        else {
            Korisnik k = new Korisnik();
            k.setUsername(username);
            k.setIme(ime);
            k.setPrezime(prezime);
            k.setDrzava(drzava);
            k.setGrad(grad);
            k.setEmail(email);
            k.setNaucneOblasti(oblasti);
            k.setTitula(titula);
            k.setPassword(hashed_pw);

            Role rola = new Role();
            rola.setId(new Long(1));
            rola.setName("KORISNIK");
            List<Role> lista = new ArrayList<Role>();
            lista.add(rola);
            k.setRoles(lista);

            repo.save(k);
        }



    }
}
