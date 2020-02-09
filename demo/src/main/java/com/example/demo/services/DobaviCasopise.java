package com.example.demo.services;

import com.example.demo.model.Casopis;
import com.example.demo.repo.CasopisRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DobaviCasopise implements JavaDelegate {

    @Autowired
    CasopisRepo casopisRepo;

    @Override
    public void execute(DelegateExecution execution){
        ArrayList<Casopis> casopisi = (ArrayList) casopisRepo.findAll();

        execution.setVariable("casopisi", casopisi);


    }
}
