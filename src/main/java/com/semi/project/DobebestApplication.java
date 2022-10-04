package com.semi.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // DB에러 땜에 사용금지;

public class DobebestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DobebestApplication.class, args);
	}

}
