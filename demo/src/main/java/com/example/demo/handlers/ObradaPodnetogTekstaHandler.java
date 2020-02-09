package com.example.demo.handlers;

import com.example.demo.model.Casopis;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObradaPodnetogTekstaHandler implements ExecutionListener {

    public void notify(DelegateExecution execution){
        execution.setVariable("casopisi", new ArrayList<Casopis>());
    }
}
