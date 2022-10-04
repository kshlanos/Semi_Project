package com.semi.project.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.semi.project")
public class DobebestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DobebestApplication.class, args);
	}

}
