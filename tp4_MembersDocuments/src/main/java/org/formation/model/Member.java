package org.formation.model;

import java.util.*;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.formation.controller.MemberViews;


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
	private String password;

	@JsonView(MemberViews.List.class)
	private String nom,prenom;

	@JsonView(MemberViews.List.class)
	private int age;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date registeredDate;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonView(MemberViews.Detail.class)
	private List<Document> documents = new ArrayList<>();

	
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
