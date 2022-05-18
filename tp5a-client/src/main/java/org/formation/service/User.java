package org.formation.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private long id;
	@JsonProperty("nom")
	private String name;
	private String prenom;
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", prenom=" + prenom + "]";
	}
	
	
}
