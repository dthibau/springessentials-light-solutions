package org.formation.service;

import org.formation.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class RestService {

    WebClient client;
    
    public RestService(WebClient.Builder builder) {
        client = builder.baseUrl("http://localhost:8080").build();
    }
    
    public Mono<User> loadUser(long id) {
        
        return client.get()
                .uri("/members/1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(User.class);
    }
}
