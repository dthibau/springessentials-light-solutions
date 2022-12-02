package org.formation.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(path = "/api/members")
public class MemberRestController {

	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping
	@JsonView(MemberViews.List.class)
	public List<Member> findAll(@RequestParam(required = false) String q) {
		if ( q!= null && !q.isEmpty() ) {
			return memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase(q, q);
		}
		return memberRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	@JsonView(MemberViews.Detail.class)
	public Member findById(@PathVariable Long id) {
		return memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException("No such member "+id));
	}
	
	@PostMapping
	@JsonView(MemberViews.Create.class)
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
		member = memberRepository.save(member);
		
		return new ResponseEntity<Member>(member, HttpStatus.CREATED);
	}
	
	@PutMapping
	@JsonView(MemberViews.List.class)
	public ResponseEntity<Void> replace(@Valid @RequestBody Member member) {
		
		memberRepository.findById(member.getId()).orElseThrow(() -> new EntityNotFoundException());
		memberRepository.save(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping
	@JsonView(MemberViews.List.class)
	public ResponseEntity<Void> partialUpdate(@RequestBody Member partialMember) {
		
		Member originalMember = memberRepository.findById(partialMember.getId()).orElseThrow(() -> new EntityNotFoundException());
		Member mergedMember = _mergeMember(originalMember,partialMember);
		memberRepository.save(mergedMember);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		memberRepository.deleteById(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

	private Member _mergeMember(Member original, Member partial) {
		Member result = new Member();
		result.setId(partial.getId());
		result.setAge(partial.getAge() != 0 ? partial.getAge() : original.getAge());
		result.setEmail(partial.getEmail() != null ? partial.getEmail() : original.getEmail());
		result.setNom(partial.getNom() != null ? partial.getNom() : original.getNom());
		result.setPassword(partial.getPassword() != null ? partial.getPassword() : original.getPassword());
		result.setPrenom(partial.getPrenom() != null ? partial.getPrenom() : original.getPrenom());
		
		return result;
	}
}
