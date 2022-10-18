package org.formation.resource;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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
@RequestMapping("/api/members")
public class MemberResource {

	@Autowired
	MemberRepository memberRepository;


	@GetMapping
	@JsonView(MemberViews.List.class)
	public List<Member> search(@RequestParam(required = false) String q) {
		if (q == null || q.trim().isEmpty()) {
			return memberRepository.findAll();
		}
		return memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase(q, q);
	}

	@GetMapping("/{id}")
	@JsonView(MemberViews.Detail.class)
	public Member getById(@PathVariable long id) throws EntityNotFoundException {

		return memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException("No such Member " + id));

	}

	@PostMapping()
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {

		return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(member));

	}

	@PutMapping
	public ResponseEntity<Member> replace(@Valid @RequestBody Member member) throws EntityNotFoundException {
		memberRepository.findById(member.getId())
				.orElseThrow(() -> new EntityNotFoundException("Id " + member.getId()));

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberRepository.save(member));

	}

	@PatchMapping("/{id}")
	public ResponseEntity<Member> patch(@PathVariable long id, @RequestBody Member member)
			throws EntityNotFoundException {

		Member originalMember = memberRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id " + id));

		member = _merge(member, originalMember);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberRepository.save(member));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) throws EntityNotFoundException {
		memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id " + id));
		memberRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	private Member _merge(Member targetMember, Member complementMember) {
		if (targetMember.getNom() == null) {
			targetMember.setNom(complementMember.getNom());
		}
		if (targetMember.getPrenom() == null) {
			targetMember.setPrenom(complementMember.getPrenom());
		}
		if (targetMember.getEmail() == null) {
			targetMember.setEmail(complementMember.getEmail());
		}
		if (targetMember.getPassword() == null) {
			targetMember.setPassword(complementMember.getPassword());
		}
		if (targetMember.getAge() == -1) {
			targetMember.setAge(complementMember.getAge());
		}
		return targetMember;
	}
}
