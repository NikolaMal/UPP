package com.example.demo.handlers;

import com.example.demo.model.NaucnaOblast;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistrationProcessHandler implements ExecutionListener {

    @Autowired
    IdentityService identityService;

    @Autowired
    AuthorizationService authorizationService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
//        User test_urednik = identityService.newUser("urednik");
//        test_urednik.setFirstName("urednik");
//        test_urednik.setLastName("urednik");
//        test_urednik.setEmail("urednik");
//        test_urednik.setPassword("urednik");

//        identityService.saveUser(test_urednik);

        Group recenzenti = identityService.createGroupQuery().groupId("recenzenti").singleResult();

        ArrayList<NaucnaOblast> oblasti = new ArrayList<NaucnaOblast>();


        execution.setVariable("oblasti", oblasti);



        if(recenzenti == null){
            recenzenti = identityService.newGroup("recenzenti");
            recenzenti.setName("recenzenti");
            identityService.saveGroup(recenzenti);
        }

        Group urednici = identityService.createGroupQuery().groupId("urednici").singleResult();

        if(urednici == null){
            urednici = identityService.newGroup("urednici");
            urednici.setName("urednici");
            identityService.saveGroup(urednici);
        }

        Group korisnici = identityService.createGroupQuery().groupId("korisnici").singleResult();


        if(korisnici == null){
            korisnici = identityService.newGroup("korisnici");
            korisnici.setName("korisnici");
            identityService.saveGroup(korisnici);
        }


//        identityService.createMembership(test_urednik.getId(), "urednici");

        execution.setVariable("odobrena_recenzentura", false);


    }
}
