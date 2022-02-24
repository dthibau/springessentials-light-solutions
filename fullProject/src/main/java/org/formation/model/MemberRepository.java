package org.formation.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

	
	/**
	 * Retourne un membere via son email;
	 * @param email
	 * @return
	 */
	Optional<Member> findByEmail(String email);
	
	/**
	 * @param email
	 * @param password
	 */
	Optional<Member> findByEmailAndPassword(String email, String password);
	
	/**
	 * @param partialNom
	 * @param partialPrenom
	 * @return
	 */
	public List<Member> findByNomOrPrenomContainsIgnoreCase(String partialNom, String partialPrenom);
	
	/**
	 * Recherche les membres dont le nom contient la chaîne de caractères q;
	 * @param q
	 * @return
	 */
	List<Member> findByNomContainsIgnoreCase(String q);
	
	
	
	/**
	 * Chargement du membre et de ses documents associés.
	 * @param id
	 * @return
	 */
	@Query("from Member m left join fetch m.documents where m.id =:id")
	public Optional<Member> fullLoad(@Param("id") Long id);
	
	
}
