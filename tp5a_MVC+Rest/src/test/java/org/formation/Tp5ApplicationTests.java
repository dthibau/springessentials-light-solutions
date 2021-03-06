package org.formation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.formation.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class Tp5ApplicationTests {

	@Autowired
	MockMvc mvc;
	
	
	@Test
	@WithMockUser
	void whenMemberExistReturn200() throws Exception {
			
		mvc.perform(get("/members/1").accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk());
			
	}
	
	@Test
	@WithMockUser
	void whenMemberNotExistReturn404() throws Exception {		
		mvc.perform(get("/members/99").accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isNotFound());		
	}

}
