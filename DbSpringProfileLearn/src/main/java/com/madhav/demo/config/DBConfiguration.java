package com.madhav.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {
 private String driverClassName;
 private String url;
 private String password;
 private String username;
 
 
 public String getDriverClassName() {
	return driverClassName;
}


public void setDriverClassName(String driverClassName) {
	this.driverClassName = driverClassName;
}


public String getUrl() {
	return url;
}


public void setUrl(String url) {
	this.url = url;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


 @Profile("dev")
 @Bean
 public String devDBConnection()
 {
	 System.out.println("This is Dev Profile");
	 System.out.println(driverClassName);
	 System.out.println(url);
	 System.out.println(password);
	 System.out.println(username);
	 return "dev env successfull";
 }
}
