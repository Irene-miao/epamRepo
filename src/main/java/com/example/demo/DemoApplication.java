package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	TutorialRepository tutorialRepository;
	 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Insert 2 test data when the application started.
		tutorialRepository.save( new Tutorial("Introduction to Spring Boot", "Learn the basics of Spring Boot framework.", true));
		tutorialRepository.save(new Tutorial("RESTful API with Java", "Build a RESTful API using Java and Spring.", false));
	}

}
