package org.formation.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = { "spring.jpa.properties.hibernate.hbm2ddl.import_files=" })
public class MemberServiceTest {

	@Autowired
	MemberService documentService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Test
	void testImport() {
		long initialMemberCount = memberRepository.count();
		long initialDocumentCount = documentRepository.count();
		
		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		
		documentService.importDocument(doc);
		
		assertAll("Adding One Member 2 docs",
				() -> assertEquals(initialMemberCount, memberRepository.count()),
				() -> assertEquals(initialDocumentCount + initialMemberCount, documentRepository.count()));

	}
}
