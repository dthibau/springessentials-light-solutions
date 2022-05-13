package org.formation.repository;

import java.util.List;
import java.util.Optional;

import org.formation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

	public Optional<Member> findByEmail(String email);
	
	public Optional<Member> findByEmailAndPassword(String email, String password);
	
	public List<Member> findByNomContainingIgnoreCase(String nom);
	
	@Query("from Member m where (upper(m.nom) like upper(:q) or upper(m.prenom) like upper(:q))")
	public List<Member> findByNomOrPrenomContainingAllIgnoreCase(@Param("q") String q);

	
	@Query("from Member m left join fetch m.documents where m.id =:id")
	public Optional<Member> fullLoad(@Param("id") Long id);
}
