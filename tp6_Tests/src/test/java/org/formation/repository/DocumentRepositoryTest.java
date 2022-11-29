package org.formation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;

@DataJpaTest
public class DocumentRepositoryTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DocumentRepository repository;
    
    
    @BeforeEach
    public void setup() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	if ( !(beanName.startsWith("org.") || beanName.startsWith("spring.")) )
            System.out.println(beanName);
        }
    }
    @Test
    public void testFindByOwner() throws Exception {
    	Member firstMember = new Member();
		firstMember.setEmail("david@gmail.com");
		firstMember.setPassword("secret");
		firstMember.setPrenom("");
		firstMember.setNom("");
		firstMember.setRegisteredDate(new Date());
		Document doc1 = new Document();
		doc1.setName("Toto");
		Document doc2 = new Document();
		firstMember.addDocument(doc1);
		firstMember.addDocument(doc2);	
        this.entityManager.persist(firstMember);
        
        List<Document> docs = this.repository.findByOwner(firstMember);
        assertThat(docs.size()).isEqualTo(2);
        assertThat(docs.get(0).getName()).isEqualTo("Toto");

    }
    
    
}
