package com.example.demo.handlers;

import com.example.demo.model.Korisnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.model.Role;
import com.example.demo.repo.KorisnikRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadRecenzent implements TaskListener {

    @Autowired
    FormService formService;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Override
    public void notify(DelegateTask delegateTask){
        DelegateExecution execution = delegateTask.getExecution();
        ArrayList<FormField> fields = (ArrayList) formService.getTaskFormData(delegateTask.getId()).getFormFields();
        ArrayList<NaucnaOblast> oblasti = (ArrayList) execution.getVariable("oblasti");
        boolean recenzent_odlicno = false;
        boolean urednik_odlicno = false;


        ArrayList<Korisnik> korisnici = (ArrayList) korisnikRepo.findAll();

        for(Korisnik k : korisnici){
            List<Role> rols = k.getRoles();
            System.out.println(k.getUsername() + "------------");
            for(Role rol : rols){
                System.out.println(rol.getId());
            }

        }

        for(FormField field : fields){
            if(field.getId().equals("recenzent")){
                EnumFormType enumType = (EnumFormType) field.getType();
                for(Korisnik k : korisnici){
                    recenzent_odlicno = false;
                    for(Role rol : k.getRoles()){
                        if(rol.getId() == 3){
                            recenzent_odlicno = true;
                        }
                    }
                    if(recenzent_odlicno){
                        System.out.println("ID role je " + k.getRoles().get(0).getId());
                        enumType.getValues().put(k.getUsername(), k.getUsername());
                    }
                }
            }
        }

        for(FormField field : fields){
            if(field.getId().equals("urednik")){
                EnumFormType enumType = (EnumFormType) field.getType();
                for(Korisnik k : korisnici){
                    urednik_odlicno = false;
                    for(Role rol : k.getRoles()){
                        if(rol.getId() == 4){
                            urednik_odlicno = true;
                        }
                    }
                    if(urednik_odlicno){
                        System.out.println("ID role je " + k.getRoles().get(0).getId());
                        enumType.getValues().put(k.getUsername(), k.getUsername());
                    }
                }
            }
        }

        for(FormField field : fields){
            if(field.getId().equals("urednici_provera")){
                EnumFormType enumType = (EnumFormType) field.getType();
                List<Korisnik> urednici = (ArrayList) execution.getVariable("urednici");
                for(Korisnik k : urednici){

                        enumType.getValues().put(k.getUsername() + "(urednik)", k.getUsername()+ "(urednik)");

                }
            }
        }

        for(FormField field : fields){
            if(field.getId().equals("urednici_provera")){
                EnumFormType enumType = (EnumFormType) field.getType();
                List<Korisnik> recenzenti = (ArrayList) execution.getVariable("recenzenti");
                for(Korisnik k : recenzenti){

                    enumType.getValues().put(k.getUsername()+ "(recenzent)", k.getUsername()+ "(recenzent)");

                }
            }
        }

        List<Korisnik> recenzenti = new ArrayList<>();

        execution.setVariable("lista_recenzenata", recenzenti);




    }
}
