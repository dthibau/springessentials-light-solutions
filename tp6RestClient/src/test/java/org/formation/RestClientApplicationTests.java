package org.formation;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.assertj.core.api.Assertions.assertThat;

import org.formation.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class RestClientApplicationTests {

	@Autowired
	WebClient.Builder builder;
	
	WebClient client;
	
	
	private WebTestClient webTestClient;
	
	@BeforeEach
	void buildClient() {
		client = builder.baseUrl("http://localhost:8080").build();
	}
	
	@Test
	void contextLoads() {
	}

	@Test
	void getMemberById() {
		
		User m = client.get()
		      .uri("/api/members/{id}",1l)
		      .accept(MediaType.APPLICATION_JSON)
		      .retrieve().bodyToMono(User.class).block();
		
		System.out.println(m);
		
	}
	
	@Test
	void testMemberById() {
		
		webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
		webTestClient.get()
		      .uri("/api/members/1")
		      .accept(MediaType.APPLICATION_JSON)
		     .exchange().expectStatus().isOk().expectBody().consumeWith(b -> {
		    	 System.out.println(b);
		     });
		
		
	}
	
}
