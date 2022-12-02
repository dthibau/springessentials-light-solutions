package org.formation.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberRestController.class)
public class MemberRestControllerTest {

	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	MemberRepository memberRepository;
	
	@Test
	@WithMockUser
	public void testGetMember() throws Exception {
		Member result = new Member();
		result.setPrenom("David");
		
		given(this.memberRepository.fullLoad(1l)).willReturn(Optional.of(result));
		
		mvc.perform(get("/api/members/1")).andExpect(status().isOk())
				                          .andExpect(jsonPath("$.prenom").value("David"));
	}
}
