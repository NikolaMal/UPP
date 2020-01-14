package com.example.demo.handlers;

import com.example.demo.model.NaucnaOblast;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddCasopisProcessHandler implements ExecutionListener {

    @Autowired
    IdentityService identityService;

    @Autowired
    AuthorizationService authorizationService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        ArrayList<NaucnaOblast> oblasti = new ArrayList<NaucnaOblast>();

        execution.setVariable("oblasti", oblasti);

    }
}
