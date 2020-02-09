package com.example.demo.handlers;

import com.example.demo.model.Casopis;
import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repo.CasopisRepo;
import com.example.demo.repo.KorisnikRepo;
import com.example.demo.repo.NaucnaOblastRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadNoviRecNobl implements TaskListener {

    @Autowired
    CasopisRepo casopisRepo;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    NaucnaOblastRepo naucnaOblastRepo;

    @Autowired
    FormService formService;
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Usao u loadNOVIrecenzentnobl");
        DelegateExecution execution = delegateTask.getExecution();

        System.out.println("1");

        Casopis c = casopisRepo.findOneByIme((String) execution.getVariable("casopis"));



        System.out.println("2");




        List<FormField> fields = formService.getTaskFormData(delegateTask.getId()).getFormFields();




        System.out.println("3");



        for(FormField f : fields){
            if(f.getId().equals("recenzentnovi")){
                EnumFormType e = (EnumFormType) f.getType();
                System.out.println(e.getValues());
            }
        }


        NaucnaOblast nobl = naucnaOblastRepo.findOneByIme((String) execution.getVariable("naucna_oblast"));


        for(FormField field : fields){
            if(field.getId().equals("recenzentnovi")){
                EnumFormType enumFormType = (EnumFormType) field.getType();

                System.out.println(enumFormType.getValues());
                for(Korisnik r : c.getRecenzenti()){
                    for(NaucnaOblast no : r.getNaucneOblasti()){
                        if(no.getIme().equals(nobl.getIme())){
                            enumFormType.getValues().put(r.getUsername(), r.getUsername());
                        }
                    }
                }


            }
        }

        execution.setVariable("dodato", true);
    }
}
