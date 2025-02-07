package org.formation.controller;


import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @MockitoBean
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    void testFindOne() throws Exception {
        Member member = new Member();
        member.setId(1L);
        when(memberRepository.fullLoad(1L)).thenReturn(Optional.of(member));

        mockMvc.perform(get("/api/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));


        verify(memberRepository).fullLoad(1L);

        mockMvc.perform(get("/api/members/999"))
                .andExpect(status().isNotFound());

    }
}
