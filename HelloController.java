/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.auth;


//import java.io.FileReader;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import com.example.auth.jwt.JWTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author alfayo
 */
@RestController
public class HelloController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTResponse jwtResponse;
    //@PostMapping("/authenticate")
    @PostMapping(path = "/authenticate", 
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> hello(@RequestBody User user1) throws FileNotFoundException, IOException{
   
        UsernamePasswordAuthenticationToken loginCredentials =
                new UsernamePasswordAuthenticationToken(
                        user1.getUsername(), user1.getPassword());
         Authentication authentication =
                authenticationManager.authenticate(loginCredentials);
        /*InputStream inputStream = new FileInputStream(new File("src/main/java/com/example/auth/requestFile.json"));
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(inputStream, User.class);*/
        
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtResponse.createToken(user);
        
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .build();
        
        
    }
    

}  
