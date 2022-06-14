package org.formation.service;

import javax.transaction.Transactional;

import org.formation.model.Document;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	@Autowired
	MemberRepository memberRepository;
	
	@Transactional
	public void importDocument(Document doc) {
		
		memberRepository.findAll().stream().forEach(m -> {
			m.addDocument(doc);
			memberRepository.save(m);
		});
		
	}
}
