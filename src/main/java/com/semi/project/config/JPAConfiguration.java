package com.semi.project.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration 
@EntityScan(basePackages = {"com.semi.project"}) 
@EnableJpaRepositories(basePackages = "com.semi.project") 
public class JPAConfiguration {

}
