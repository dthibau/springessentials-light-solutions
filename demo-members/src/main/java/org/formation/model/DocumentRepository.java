package org.formation.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("select m.documents from Member m where m.nom = :ownerName")
	public List<Document> findByOwnerName(String ownerName);
	
	@Query("select m.documents from Member m where m.id = :ownerId")
	public List<Document> findByOwnerId(Long ownerId);
}
