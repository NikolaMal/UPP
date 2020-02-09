package com.example.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SacuvajKomentar implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        List<String> komentari = (ArrayList) delegateTask.getExecution().getVariable("lista_komentara");

        System.out.println(komentari);
        System.out.println(delegateTask.getExecution().getVariable("recenzent_komentar"));

        komentari.add((String) delegateTask.getExecution().getVariable("recenzent_komentar"));

        delegateTask.getExecution().setVariable("lista_komentara", komentari);

        List<String> komentari_autor = (ArrayList) delegateTask.getExecution().getVariable("lista_komentara_autor");

        komentari_autor.add((String) delegateTask.getExecution().getVariable("recenzent_komentar_autor"));

        delegateTask.getExecution().setVariable("lista_komentara_autor", komentari_autor);
    }
}
