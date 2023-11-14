package org.formation.repository;

import java.util.List;

import org.formation.model.Customer;
import org.formation.model.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void _playWithMongo() {
        customerRepository.deleteAll();
        // save a couple of customers
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));
        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");

        List<Customer> customers = customerRepository.findAll().collectList().block();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
        // fetch an individual customer
        System.out.println("Customer found with  findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(customerRepository.findByFirstName("Alice").collectList().block());
        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        customers = customerRepository.findByLastName("Smith").collectList().block();

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

}
