package com.example.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service
public class NoviRec implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.getExecution().setVariable("jedan_recenzent", delegateTask.getExecution().getVariable("recenzentnovi"));

    }
}
