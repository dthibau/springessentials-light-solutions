package org.formation.tp6restclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void testFindById() {
        // TODO
        User user = memberService.findUserById(1l);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    void testAll() {
        // TODO
        Arrays.stream(memberService.findAllUsers("THI")).forEach(System.out::println);
    }
    @Test
    void testCreate() {
        User user = new User("Dupont","D",System.currentTimeMillis() + "@gmail.com");
        System.out.println(memberService.createUser(user));
    }
}
