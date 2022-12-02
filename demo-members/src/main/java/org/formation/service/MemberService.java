package org.formation.service;

import javax.transaction.Transactional;

import org.formation.model.Document;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	public void addDocumentToAllMembers(Document document) {
		
		memberRepository.findAll().stream().forEach(m -> {
			m.addDocument(document);
			memberRepository.save(m);
		});
	}
}
