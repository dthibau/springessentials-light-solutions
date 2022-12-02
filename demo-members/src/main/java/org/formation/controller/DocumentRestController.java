package org.formation.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members/{memberId}/documents")
public class DocumentRestController {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	MemberRepository memberRepository;

	@GetMapping
	public List<Document> findDocuments(@PathVariable Long memberId) {
		memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException());

		return documentRepository.findByOwnerId(memberId);
	}

	@PostMapping
	public ResponseEntity<Void> addDocument(@PathVariable Long memberId, @RequestBody @Validated Document document) {

		Member member = memberRepository.fullLoad(memberId).orElseThrow(() -> new EntityNotFoundException());

		memberRepository.save(member);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
