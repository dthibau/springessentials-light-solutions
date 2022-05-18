package org.formation.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.formation.controller.MemberRestController;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberRestController.class)
public class MemberRestControllerTest {

	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	MemberRepository memberRepository;
	
	@MockBean
	DocumentRepository documentRepository;
	
	
	
	@Test
	@WithMockUser
	void whenMemberExistReturn200() throws Exception {
		
		when(memberRepository.fullLoad(1l)).thenReturn(Optional.of(new Member()));
		
		mvc.perform(get("/members/1").accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk());
			
	}
	
	@Test
	@WithMockUser
	void whenMemberNotExistReturn404() throws Exception {
		when(memberRepository.fullLoad(1l)).thenReturn(Optional.empty());
		
		mvc.perform(get("/members/1").accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isNotFound());		
	}
}
