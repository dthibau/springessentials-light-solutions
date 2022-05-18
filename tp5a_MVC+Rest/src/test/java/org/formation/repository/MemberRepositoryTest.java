package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	
	@BeforeEach
	public void displayBeans() {
		
		for ( String bName : context.getBeanDefinitionNames() ) {
			System.out.println("Beans : " + bName);
		}
	}
	
	@Test 
	void testFindByName() {
		Member m = Member.builder().email("d@gmail.com").nom("ABCCD").password("secret").build();
				
		testEntityManager.persist(m);
		
//		assertEquals(1, memberRepository.findByNomOrPrenomContainingAllIgnoreCase("CC").size());
	}
	
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
