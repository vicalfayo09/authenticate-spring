/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.auth;

//import org.springframework.web.bind.annotation.GetMapping;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author alfayo
 */
@RestController
public class HelloController {
  
    //@PostMapping("/authenticate")
    @PostMapping("/authenticate")
    public Map<String, String> hello(/*@RequestParam Map<String, String> payload*/) throws FileNotFoundException{
      /*  
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(payload);
            return jsonPayload;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        InputStream inputStream = new FileInputStream(new File("src/main/java/com/example/auth/requestFile.json"));
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>(){};
        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mapper.writeValueAsString( inputStream);
    } 
    public void getPayload() throws JsonProcessingException{
        System.out.println(new ObjectMapper().writeValueAsString(new File("src/main/java/com/example/auth/requestFile.json")));
    }
}
