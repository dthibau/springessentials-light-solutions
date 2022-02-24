package org.formation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.formation.controller.views.MemberViews;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;



@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
@Data
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MemberViews.List.class)
	private long id;
	
	@NotNull
	@JsonView(MemberViews.List.class)
	private String email,password;
	
	@JsonView(MemberViews.List.class)
	private String nom,prenom;
	
	@JsonView(MemberViews.List.class)
	private int age;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(MemberViews.List.class)
	private Date registeredDate;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonView(MemberViews.Detail.class)
	private Set<Document> documents = new HashSet<Document>();

	
	public void addDocument(Document document) {
		this.documents.add(document);
	}
	
	@Transient
	public String getNomComplet() {
		return getPrenom() + " " + getNom();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return id == other.id;
	}

	
	
}
