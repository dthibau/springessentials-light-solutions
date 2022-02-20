package org.formation;

import org.formation.model.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberDocumentsApplicationTests {
	
	@Autowired
	DocumentRepository documentRepository;
	

	@Test
	void contextLoads() {
		

	}

	
}
