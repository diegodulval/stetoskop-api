package com.dulval.stetoskop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StetoskopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StetoskopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
	}	
}
