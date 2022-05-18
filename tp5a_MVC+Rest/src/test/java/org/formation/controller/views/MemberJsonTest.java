package org.formation.controller.views;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.ApplicationContext;

@JsonTest
public class MemberJsonTest {

	
	@Autowired
	JacksonTester<Member> json;
	
	@Autowired
	ApplicationContext context;
	
	Member member;
	
	@BeforeEach
	void initAMember() {
		Document doc1 = new Document();
		doc1.setId(1);
		doc1.setName("DOC1");
		Document doc2 = new Document();
		doc2.setId(2);
		doc2.setName("DOC2");
		
		Set<Document> docs = new HashSet<>();
		docs.add(doc1);
		docs.add(doc2);
		
		member = Member.builder().age(18).email("d@gmail.com").id(1).nom("NOM").password("PWD").prenom("PRENOM").documents(docs).build();
	}
	
	@Test
	void testSimpleSerialisation() throws IOException {
		
		JsonContent<Member> jsonContent = json.forView(MemberViews.Simple.class).write(member);
		
		assertThat(jsonContent).doesNotHaveJsonPath("@.password")
		                        .doesNotHaveJsonPath("@.documents")
		                        .hasJsonPath("@.nom")
		                        .extractingJsonPathStringValue("@.nom").isEqualTo("NOM");
	}
	
	@Test
	void testFullSerialisation() throws IOException {
		
		JsonContent<Member> jsonContent = json.forView(MemberViews.Complet.class).write(member);
		
		assertThat(jsonContent).doesNotHaveJsonPath("@.password")
		                        .hasJsonPathArrayValue("@.documents");
	}
}
