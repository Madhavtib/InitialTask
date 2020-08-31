package com.madhav.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.madhav.demo.config.DBConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(DBConfiguration.class)
public class DbSpringProfileLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbSpringProfileLearnApplication.class, args);
	}

}
