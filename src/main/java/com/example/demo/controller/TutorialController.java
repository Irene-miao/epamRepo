package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
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

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class TutorialController {
	  

@Autowired
TutorialRepository repo;

    @GetMapping(path="/tutorials")
    public ResponseEntity<String> getTutorial(@RequestParam String label) {
        List<Tutorial> list = new LinkedList<>();
        JsonArrayBuilder array = Json.createArrayBuilder();
        JsonObject obj = Json.createObjectBuilder().build();


         try { 
            if (label.equalsIgnoreCase("true") || label.equalsIgnoreCase("false")) {
               list =  (label.equalsIgnoreCase("true")) ?repo.findByPublished(true) : repo.findByPublished(false);
                
          
            } else {
              list =  repo.findByTitleContainingIgnoreCase(label);
            
             }
         
         }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
            .add("error", e.getMessage())
            .build().toString());
         }

         for (Tutorial t: list){
            obj = Json.createObjectBuilder().add("id", t.getId())
         .add("title", t.getTitle())
         .add("description", t.getDescription())
         .add("published", t.isPublished()).build();
         array.add(obj);
         }
        
         String json = array.build().toString();
      
         return ResponseEntity.status(HttpStatus.OK)
         .contentType(MediaType.APPLICATION_JSON)
         .body(json);
    }

    @PostMapping(path="/tutorials/create", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial tut = new Tutorial();

         try {
             tut = new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished());
            repo.save(tut);
         }catch (Exception e) {
             e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
            .add("error", e.getMessage())
            .build().toString());
         }

         JsonObject obj = Json.createObjectBuilder()
         .add("title", tut.getTitle())
         .add("description", tut.getDescription())
         .add("published", tut.isPublished())
         .build();

         return ResponseEntity.status(HttpStatus.OK)
         .contentType(MediaType.APPLICATION_JSON)
         .body(obj.toString());
         

    }

    @DeleteMapping(path="/tutorials/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable Long id){

      System.out.println(id);
        try {
            Optional<Tutorial> tutorial = repo.findById(id);
            System.out.println(tutorial.get());
            if (tutorial.isPresent()){
                repo.delete(tutorial.get());
            } 
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                Json.createObjectBuilder()
                .add("error", "Failure in deleting tutorial")
                .build().toString()
            );
        }

        return ResponseEntity.ok().body(Json.createObjectBuilder()
        .add("message", "Success in deleting tutorial")
        .build().toString());
    }

    @PutMapping(path="/tutorials/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial){

   
        try {
            Optional<Tutorial> tut = repo.findById(id);
            System.out.println(tut.get());
            if (tut.isPresent()){
             Tutorial foundTutorial = tut.get();
              foundTutorial.setTitle(tutorial.getTitle());
              foundTutorial.setDescription(tutorial.getDescription());
              foundTutorial.setPublished(tutorial.isPublished());
              repo.save(foundTutorial);
            } 
        }catch(Exception e) {
            e.printStackTrace();
             return ResponseEntity.status(500).body(
                Json.createObjectBuilder()
                .add("error", "Failure in updating tutorial")
                .build().toString()
            );
        }

        return ResponseEntity.ok().body(Json.createObjectBuilder()
        .add("message", "Success in updating tutorial")
        .build().toString());
    }


}
