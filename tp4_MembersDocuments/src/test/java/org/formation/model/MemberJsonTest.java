package org.formation.model;

import org.formation.controller.MemberViews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class MemberJsonTest {

    @Autowired
    JacksonTester<Member> json;
    Member member = new Member();

    @Autowired
    ApplicationContext context;
    @BeforeEach
    void setUp() {
        member.setId(1L);
        member.setNom("Doe");
        member.setPrenom("John");
        member.setEmail("d");
        Document doc = new Document();
        doc.setName("doc1");
        member.getDocuments().add(doc);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
    @Test
    void serializeWithViewList() throws Exception {

        assertThat(this.json.forView(MemberViews.List.class).write(member))
                .hasJsonPathNumberValue("@.id")
                .hasEmptyJsonPathValue("@.documents")
                .extractingJsonPathStringValue("@.nom").isEqualTo("Doe");
    }
    @Test
    void serializeWithViewDetails() throws Exception {

        assertThat(this.json.forView(MemberViews.Detail.class).write(member))
                .hasJsonPathNumberValue("@.id")
                .hasJsonPathArrayValue("@.documents")
                .extractingJsonPathStringValue("@.nom").isEqualTo("Doe");
    }
}
