package org.formation.controller;

import java.util.List;
import java.util.Optional;

import org.formation.controller.views.MemberViews;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberRepository memberRepository;

	public MemberRestController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@GetMapping
	@JsonView(MemberViews.List.class)
	public List<Member> findAll(@RequestParam Optional<String> q) throws MemberNotFoundException {
	    
	    if ( !q.isEmpty() ) {
	        return memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase(q.get(),q.get());
	    } else {
	        return memberRepository.findAll();
	    }
	}
	

	@GetMapping("/{id}")
	@JsonView(MemberViews.Detail.class)
	public Member getById(@PathVariable long id) throws MemberNotFoundException {

		return memberRepository.fullLoad(id).orElseThrow(() -> new MemberNotFoundException("No such Member "+id));

	}
		
	@PostMapping()
	@JsonView(MemberViews.Detail.class)
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(memberRepository.save(member));

	}
	
	@PutMapping
	@JsonView(MemberViews.Detail.class)
	public ResponseEntity<Member> replace(@Valid Member member) throws MemberNotFoundException {
		memberRepository.findById(member.getId()).orElseThrow(
				() -> new MemberNotFoundException("Id " + member.getId()));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	
	@PatchMapping("/{id}")
	@JsonView(MemberViews.Detail.class)
	public ResponseEntity<Member> patch(@PathVariable long id, @RequestBody Member member) throws MemberNotFoundException {

		Member originalMember = memberRepository.findById(id).orElseThrow(
				() -> new MemberNotFoundException("Id " + id));
		
		member = _merge(member,originalMember);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) throws MemberNotFoundException {
		memberRepository.findById(id).orElseThrow(
				() -> new MemberNotFoundException("Id " + id));
		memberRepository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();


	}

	private Member _merge(Member targetMember, Member complementMember) {
		if ( targetMember.getNom() == null ) {
			targetMember.setNom(complementMember.getNom());
		}
		if ( targetMember.getPrenom() == null ) {
			targetMember.setPrenom(complementMember.getPrenom());
		}
		if ( targetMember.getEmail() == null ) {
			targetMember.setEmail(complementMember.getEmail());
		}
		if ( targetMember.getPassword() == null ) {
			targetMember.setPassword(complementMember.getPassword());
		}
		if ( targetMember.getAge() == -1 ) {
			targetMember.setAge(complementMember.getAge());
		}
		return targetMember;
	}
}
