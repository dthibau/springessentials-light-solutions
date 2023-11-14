package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	DataSource dataSource;

	@Test
	void testByEmailNotIgnoringCase() {

		Optional<Member> found = memberRepository.findByEmail("dthibau@wmmod.com");

		assertTrue(found.isPresent());

		Optional<Member> notFound = memberRepository.findByEmail("dthibau@WMmod.com");

		assertTrue(!notFound.isPresent());
	}
	
	@Test
	void testByFirstOrLastName() {

		List<Member> result = memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase("da","da");

		assertEquals(1,result.size());

		result = memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase("t","t");

		assertEquals(6,result.size());
	}
	
	@Test
	void testingCascading() {
		long initialMemberCount = memberRepository.count();
		long initialDocumentCount = documentRepository.count();

		Member newMember = new Member();
		newMember.setAge(18);
		newMember.setEmail("tutu@mail.com");
		newMember.setPassword("secret");

		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		Document doc2 = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy2.doc");
		doc.setUploadedDate(new Date());

		newMember.addDocument(doc);
		newMember.addDocument(doc2);

		memberRepository.save(newMember);

		assertAll("Adding One Member 2 docs",
				() -> assertEquals(initialMemberCount + 1, memberRepository.count()),
				() -> assertEquals(initialDocumentCount + 2, documentRepository.count()));

	}

	@Test
	void testingFullLoad() {
		Member id1 = memberRepository.fullLoad(1l).orElseThrow();
		Member id2 = memberRepository.findById(1l).orElseThrow();
		entityManager.close();

		id1.getDocuments().stream().forEach(System.out::println);

		assertThrows(LazyInitializationException.class, () -> id2.getDocuments().stream().forEach(System.out::println));
	}
}
