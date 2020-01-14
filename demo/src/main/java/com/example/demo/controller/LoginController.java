package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Korisnik;
import com.example.demo.model.UserInfoDTO;
import com.example.demo.model.UserTokenState;
import com.example.demo.repo.KorisnikRepo;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.TokenUtils;
import com.example.demo.security.auth.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("auth")
public class LoginController {
    @Autowired
    public TokenUtils tokenUtils;

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    public CustomUserDetailsService userDetailsService;

    @Autowired
    private KorisnikRepo korisnikRepo;



    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<UserTokenState> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){
//
//        if(!inputValid(authenticationRequest.getUsername())) {
//            return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);
//        }
        final Authentication authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        Korisnik user =  (Korisnik) authentication.getPrincipal();
        // VRATI DRUGI STATUS KOD
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt = tokenUtils.generateToken(user.getUsername(), device);
        int expiresIn = 3600;

        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }

    // proveriti jos da li je ovako dobro - istrazi
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get-user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDTO> getUser(HttpServletRequest request){
        String username = getUsernameFromRequest(request);
        UserInfoDTO ui = new UserInfoDTO();
        if(username != "" && username != null) {
            Korisnik u = (Korisnik) korisnikRepo.findOneByUsername(username);
            if(u instanceof Admin){
                u = (Admin) u;
                ui.setRole("ADMIN");
            }
            ui.setUsername(u.getUsername());
            return new ResponseEntity<UserInfoDTO>(ui, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
