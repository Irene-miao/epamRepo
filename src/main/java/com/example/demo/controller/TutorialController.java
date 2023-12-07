package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;

@RestController
@RequestMapping
public class TutorialController {
	  
    @GetMapping(path="/tutorials")
    public ResponseEntity<String> getTutorial() {
         try { 

         }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
            .add("error", e.getMessage())
            .build().toString());
         }

         return ResponseEntity.status(HttpStatus.OK)
         .contentType(MediaType.APPLICATION_JSON)
         .body(emotion);
    }

    @PostMapping(path="/tutorials/create")
    public ResponseEntity<String> createTutorial() {
         try {

         }catch (Exception e) {
             e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
            .add("error", e.getMessage())
            .build().toString());
         }

         return ResponseEntity.status(HttpStatus.OK)
         .contentType(MediaType.APPLICATION_JSON)
         .body(Json.createObjectBuilder()
         .add("message", )
         .build().toString());
         

    }

     @DeleteMapping(path="/tutorials/:id")
    public ResponseEntity<String> deleteTutorial(@PathVariable Long id){

       
      
        try {

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                Json.createObjectBuilder()
                .add("error", "Failure in deleting user")
                .build().toString()
            );
        }

        return ResponseEntity.ok().body(Json.createObjectBuilder()
        .add("message", "Success in deleting tutorial")
        .build().toString());
    }


}
