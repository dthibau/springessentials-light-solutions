package org.formation.controller;

import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.formation.controller.views.MemberViews;
import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/members/{id}/documents")
public class DocumentRestController {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@GetMapping
	@JsonView(MemberViews.Complet.class)
	public Set<Document> findDocuments(@PathVariable long id) {
		Member m = memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException());
		
		return m.getDocuments();
	}
	
	@PostMapping
	public ResponseEntity<Document> addDocument(@PathVariable long id, @RequestBody Document document) {
		Member m = memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException());
		document = documentRepository.save(document);
		m.addDocument(document);
		memberRepository.save(m);
		
		return new ResponseEntity<Document>(document,HttpStatus.CREATED);
	}
}
