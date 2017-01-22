package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootSampleApplication {

	@GetMapping("/")
	String home() {
		return "Hello Spring Boot!";
	}
	
	@GetMapping(path = "{name}")
	String yourName(@PathVariable String name) {
		return "Hello " + name + "!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
