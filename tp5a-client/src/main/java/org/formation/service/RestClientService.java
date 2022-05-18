package org.formation.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

	private final RestTemplate restTemplate;
	
	
	public RestClientService(RestTemplateBuilder builder) {
	
		restTemplate = builder.rootUri("http://localhost:8080")
		       .basicAuthentication("dthibau@wmmod.com", "secret")
		       .build();
	}
	
	
	public User retreiveUser(long id) {
		
		return restTemplate.getForObject("/members/1", User.class);
		
	}
}
