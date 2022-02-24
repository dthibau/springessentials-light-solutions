package org.formation.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.formation.controller.views.MemberViews;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping
	@JsonView(MemberViews.List.class)
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(MemberViews.Detail.class)
	public Member findOne(@PathVariable Long id) throws MemberNotFoundException {
				
		return memberRepository.fullLoad(id).orElseThrow(() -> new MemberNotFoundException());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(Long id) throws MemberNotFoundException {
		
		Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException());
		
		memberRepository.delete(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

		
	@PostMapping()
	@JsonView(MemberViews.Detail.class)
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {

		member.setRegisteredDate(new Date());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(memberRepository.save(member));

	}
	
	@PutMapping
	@JsonView(MemberViews.Detail.class)
	public ResponseEntity<Member> replace(@Valid @RequestBody Member member) throws MemberNotFoundException {
		Member originalMember = memberRepository.findById(member.getId()).orElseThrow(
				() -> new MemberNotFoundException());
		member.setDocuments(originalMember.getDocuments());
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	@PatchMapping("/{id}")
	public ResponseEntity<Member> patch(@PathVariable long id, @RequestBody Member member) throws MemberNotFoundException {

		Member originalMember = memberRepository.findById(id).orElseThrow(
				() -> new MemberNotFoundException("Id " + id));
		
		member = _merge(member,originalMember);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	
	private Member _merge(Member targetMember, Member complementMember) {
		targetMember.setId(complementMember.getId());
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
		if ( targetMember.getAge() == 0 ) {
			targetMember.setAge(complementMember.getAge());
		}
		return targetMember;
	}
}
