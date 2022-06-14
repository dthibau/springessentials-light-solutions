package org.formation;

import static org.assertj.core.api.Assertions.assertThat;

import org.formation.model.User;
import org.formation.service.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestServiceTest {

    @Autowired
    RestService restService;
    
    
    @Test
    void testLoadUser() {
        
        User user = restService.loadUser(1l).block();
        
        assertThat(user).isNotNull();
    }
}
