package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Test
	void testCascading() {
	
		long initialCount = documentRepository.count();
		
		Member m = new Member();
		m.setAge(18);
		m.setEmail("toto@gmail.com");
		m.setPassword("secret");
		Document doc1 = new Document();
		doc1.setName("powerpoint.ppt");
		Document doc2 = new Document();
		doc2.setName("word.doc");
		
		m.addDocument(doc1);
		m.addDocument(doc2);
		
		m = memberRepository.save(m);
		
		// Check that the doc has beeen saved
		assertEquals(initialCount+2, documentRepository.count());
	}

}
