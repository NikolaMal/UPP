package com.example.demo.controller;


import com.example.demo.model.Admin;
import com.example.demo.model.FormFieldsDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.UserInfoDTO;
import com.example.demo.security.TokenUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping(value = "obrada")
public class ObradaPodnetogTekstaController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/start", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasAuthority('SET_AUTOR_TASK')")
    public @ResponseBody FormFieldsDTO start(HttpServletRequest request){

        String username = getUsernameFromRequest(request);



        Map<String, Object> map = new HashMap<>();
        map.put("starter", username);
        System.out.println("Pokrece se proces obrade podnetog teksta, pokretac je " + username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("obrada_podnetog_teksta", map);



        return new FormFieldsDTO(null, null, processInstance.getId());
    }

    private String getUsernameFromRequest(HttpServletRequest request) {
        String authToken = tokenUtils.getToken(request);
        if (authToken == null) {
            return null;
        }
        String username = tokenUtils.getUsernameFromToken(authToken);
        return username;
    }

}
