package org.formation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.formation.controller.views.MemberViews;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
@Data
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MemberViews.List.class)
	private long id;
	
	
	@Column(unique=true)
	@JsonView(MemberViews.List.class)
	private String email;
	
	@NotNull
	@JsonView(MemberViews.Creation.class)
	private String password;
	
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
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
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
		if (id != other.id)
			return false;
		return true;
	}
	
}
