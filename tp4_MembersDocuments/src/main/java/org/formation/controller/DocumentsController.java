package org.formation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.formation.model.Document;
import org.formation.model.DocumentRepository;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members/{idMember}/documents")
public class DocumentsController {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping()
    @JsonView(MemberViews.Detail.class)
    public List<Document> getDocuments(@PathVariable long idMember) {
        Member m = memberRepository.fullLoad(idMember).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + idMember));
        return m.getDocuments();
    }
    @PostMapping()
    @JsonView(MemberViews.Detail.class)
    public Member addDocument(@PathVariable long idMember, @RequestBody @Valid Document document) {
        Member member = memberRepository.fullLoad(idMember).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + idMember));

        member.addDocument(document);

        return memberRepository.save(member);
    }
}
