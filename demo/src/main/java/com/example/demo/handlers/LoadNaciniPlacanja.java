package com.example.demo.handlers;

import com.example.demo.model.NacinPlacanja;
import com.example.demo.repo.NacinPlacanjaRepo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadNaciniPlacanja implements TaskListener {

    @Autowired
    FormService formService;

    @Autowired
    NacinPlacanjaRepo nacinPlacanjaRepo;

    @Override
    public void notify(DelegateTask delegateTask) {

        List<FormField> fields = formService.getTaskFormData(delegateTask.getId()).getFormFields();
        List<NacinPlacanja> nacini = nacinPlacanjaRepo.findAll();

        for(FormField field : fields){
            if(field.getId().equals("nacin_placanja")){
                EnumFormType enumFormType = (EnumFormType) field.getType();
                for(NacinPlacanja nacin : nacini){
                    enumFormType.getValues().put(nacin.getIme(), nacin.getIme());
                }
            }
        }
    }
}
