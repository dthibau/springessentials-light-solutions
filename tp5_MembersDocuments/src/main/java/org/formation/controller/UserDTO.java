package org.formation.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Length(min = 6, max = 40)
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
