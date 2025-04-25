/**
 * The main application class, responsible for initiating the Spring Boot application.
 */
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication {

	/**
	 * This is where I start the application. I call the SpringApplication.run method,
	 * which in turn bootstraps and launches the entire Spring Boot application.
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

}
