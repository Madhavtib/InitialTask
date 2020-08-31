package com.madhav.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/userapp")
public class UserDetailController {

		@Autowired
		private RestTemplate restTemplate;
		private String BaseUrl="http://user-detail-service";
		@GetMapping("/users")
		public List<Object> getUsers()
		{
			String url = BaseUrl + "/users";
			Object[] objects=restTemplate.getForObject("http://user-detail-service/users", Object[].class);
			System.out.println(objects.getClass());
			return Arrays.asList(objects);
		
		}
		@PostMapping("/users")
		public String addUser(@RequestBody Object obj){
			HttpHeaders headers=new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Object> entity = new HttpEntity<>(obj,headers);
			return restTemplate.exchange(BaseUrl+"/users", HttpMethod.POST,entity,String.class).getBody();
			
		}
		
}
