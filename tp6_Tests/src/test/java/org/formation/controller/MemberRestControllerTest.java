package org.formation.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


@WebMvcTest(value=MemberRestController.class)
public class MemberRestControllerTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private MemberRepository memberRepository;

	Member aMember;

	@BeforeEach
	public void setUp() {
		aMember = new Member();
		aMember.setId(1);
		aMember.setPrenom("David");

		// Display Contenu de ApplicationContext
		String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	if ( !(beanName.startsWith("org.") || beanName.startsWith("spring.")) )
                System.out.println(beanName);
        }
	}

	@Test
	public void testGetMember() throws Exception {
		given(this.memberRepository.fullLoad(1l)).willReturn(Optional.of(aMember) );
		ResultActions result = mvc.perform(get("/api/members/1"));
		MvcResult mvcResult = result.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		mvc.perform(get("/api/members/1")).andExpect(status().isOk())
		                                  .andExpect(jsonPath("$.prenom").value("David"));
	}
}
