package com.example.demo.handlers;

import com.example.demo.model.Casopis;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.model.Rad;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.NaucnaOblastRepo;
import com.example.demo.repo.RadRepo;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SacuvajRad implements TaskListener {

    @Autowired
    RadRepo radRepo;

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    NaucnaOblastRepo naucnaOblastRepo;

    @Override
    public void notify(DelegateTask delegateTask) {
        DelegateExecution execution = delegateTask.getExecution();

        Rad r = new Rad();
        Casopis c = casopisRepo.findOneByIme((String)execution.getVariable("casopis"));
        NaucnaOblast no = naucnaOblastRepo.findOneByIme((String) execution.getVariable("naucna_oblast"));

        r.setApstrakt((String) execution.getVariable("apstrakt"));
        r.setCasopis(c);
        r.setCena(30);
        r.setNaslov((String) execution.getVariable("naslov_rada"));
        r.setNaucnaOblast(no);
        r.setPrihvacen(false);

        radRepo.save(r);
    }
}
