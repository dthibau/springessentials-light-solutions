package org.formation;

import org.formation.service.RestClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tp5aClientApplicationTests {

	@Autowired
	RestClientService restClientService;
	
	@Test
	void contextLoads() {
		
		System.out.println(restClientService.retreiveUser(1l));
	}

}
