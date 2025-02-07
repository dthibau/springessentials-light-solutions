package org.formation.tp6restclient.service;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MemberService {

    private final RestClient restClient;

    public MemberService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/api/members")
                .build();
    }

    public User findUserById(Long id) {

        return restClient.get()
                .uri("/{id}",id)
                .retrieve()
                .body(User.class);

    }
    // Charger tous les membres
    public User[] findAllUsers(String q) {
        return restClient.get()
                .uri("?q={q}", q)
                .retrieve()
                .body(User[].class);
    }

    // Créer un membre
    public User createUser(User user) {
        String response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(String.class);

        System.out.println(response);

        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(User.class);
    }
}
