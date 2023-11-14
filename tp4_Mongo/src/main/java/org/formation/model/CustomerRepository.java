package org.formation.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    public Flux<Customer> findByFirstName(String firstName);
    
    public Flux<Customer> findByLastName(String lastName);
}
