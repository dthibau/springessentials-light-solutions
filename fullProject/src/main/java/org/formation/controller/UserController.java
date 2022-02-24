package org.formation.controller;

import org.formation.controller.views.MemberViews;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping
	@JsonView(MemberViews.List.class)
	public Member displayUser() throws MemberNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new MemberNotFoundException());
		
	}
}
