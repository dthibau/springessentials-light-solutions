package org.formation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.formation.model.Member;
import org.formation.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    @JsonView(MemberViews.List.class)
    public List<Member> getMembers(@RequestParam(required = false) String q) {
        if ( q != null && q.length() == 0) {
            return memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase(q,q);
        } else {
            return memberRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    @JsonView(MemberViews.Detail.class)
    public Member getMember(@PathVariable Long id) {
        return memberRepository.fullLoad(id).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(MemberViews.Create.class)
    public Member addMember(@RequestBody @Valid Member member) {
        return memberRepository.save(member);
    }
    @PutMapping("/{id}")
    @JsonView(MemberViews.List.class)
    public Member updateMember(@PathVariable Long id, @Valid @RequestBody Member member) {
        if ( !memberRepository.existsById(id) ) {
            throw new EntityNotFoundException("Member not found with id " + id);
        }
        member.setId(id);
        return memberRepository.save(member);
    }
    @PatchMapping("/{id}")
    public Member patchMember(@PathVariable Long id, @RequestBody Member member) {
        Member m = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + id));
        if ( member.getNom() != null ) {
            m.setNom(member.getNom());
        }
        if ( member.getPrenom() != null ) {
            m.setPrenom(member.getPrenom());
        }
        if ( member.getEmail() != null ) {
            m.setEmail(member.getEmail());
        }
        return memberRepository.save(m);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Member not found with id " + id));

        memberRepository.deleteById(id);
    }
}
