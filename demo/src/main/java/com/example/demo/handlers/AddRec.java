package com.example.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddRec implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        List<String> recenzenti = (ArrayList) delegateTask.getExecution().getVariable("lista_recenzenata");
        recenzenti.add((String) delegateTask.getExecution().getVariable("recenzent"));
    }
}
