package org.formation.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	
	@Query("select m.documents from Member m where m.nom = :nom")
	List<Document> findDocumentsByMembersName(@Param("nom") String membersName);
	
	@Query("select m.documents from Member m where m.nom = :id")
	List<Document> findDocumentsByMembersId(@Param("id") String membersId);
}
