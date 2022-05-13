package org.formation.controller;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web")
public class MemberController {

	@Autowired
	MemberRepository memberRepository;
	
	
	@GetMapping(path = "/register")
	public String displayForm(Model model) {
		model.addAttribute("member", new Member());
		return "register";
	}
	
	@PostMapping(path = "/register", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String submitForm(@Valid @ModelAttribute("member") Member member, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if ( result.hasErrors() ) {
			model.addAttribute("member", member);
			return "register";
		}
		redirectAttributes.addFlashAttribute("congrats", "Congratulations, you can use our servie now");
		memberRepository.save(member);
		return "redirect:/documents";
		
	}
}
