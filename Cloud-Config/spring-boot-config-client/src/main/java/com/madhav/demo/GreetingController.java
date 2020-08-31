package com.madhav.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {
	@Value("${my.greetings: default value}")
	private String greetings;

	@Value("${my.list.values}")
	private List<String> listValues;
	
	
	@Autowired
	private DbSettings dbSettings;

	public GreetingController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/greetings")
	public String greeting() {
		return greetings +"   "+ listValues + dbSettings.getConnection() + dbSettings.getHost();
	}
}
