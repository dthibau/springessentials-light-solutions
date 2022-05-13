package org.formation.repository;

import java.util.List;

import org.formation.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("select m.documents from Member m where m.nom = :nom")
	public List<Document> findByOwnersName(@Param("nom") String name);
}
