package org.formation.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.controller.views.MemberViews;
import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/members")
public class MemberRestController {

	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping
	@JsonView(MemberViews.Simple.class)
	public List<Member> findMembers(@RequestParam(value = "q", required = false) String q) {
		if ( q == null || q.isEmpty() )
			return memberRepository.findAll();
		else 
			return memberRepository.findByNomOrPrenomContainingAllIgnoreCase("%"+q+"%");
	}
	
	@GetMapping("/{id}")
	@JsonView(MemberViews.Complet.class)
	public Member findById(@PathVariable Long id) {
		return memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException("No such id "+id));
	}
	
	@PostMapping
	@JsonView(MemberViews.Simple.class)
	public ResponseEntity<Member> createMember(@RequestBody @Valid Member member) {
		return new ResponseEntity<>(memberRepository.save(member),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@JsonView(MemberViews.Simple.class)
	public Member replaceMember(@PathVariable long id, @RequestBody @Valid Member member) {
		memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such id "+id));
		member.setId(id);
		return memberRepository.save(member);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable long id) {
		memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such id "+id));
		memberRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
