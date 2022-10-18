package org.formation.resource;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members/{id}/documents")
public class MemberDocumentsResource {

	private final MemberRepository memberRepository;
	private final DocumentRepository documentRepository;

	public MemberDocumentsResource(DocumentRepository documentRepository, MemberRepository memberRepository) {
		this.documentRepository = documentRepository;
		this.memberRepository = memberRepository;

	}

	/**
	 * @param owner
	 * @return
	 * @throws MemberNotFoundException
	 */
	@GetMapping
	public List<Document> getDocuments(@PathVariable("id") Long id) throws EntityNotFoundException {

		Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id " + id));

		return documentRepository.findByOwner(member);

	}

	@PostMapping
	public ResponseEntity<Void> addDocument(@PathVariable("id") Long id, @Valid @RequestBody Document document)
			throws EntityNotFoundException {
		Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id " + id));

		member.addDocument(document);
		memberRepository.save(member);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}
