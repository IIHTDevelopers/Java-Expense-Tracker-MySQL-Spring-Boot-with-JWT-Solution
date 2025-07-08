package com.todowithjwt.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

	private int id;

	@NotBlank(message = "Expense category is required")
	private String name;

	@NotBlank(message = "Expense category is required")
	private String email;

	@NotBlank(message = "Expense category is required")
	private String password;

	private String roles;

	public UserDTO() {
		super();
	}

	public UserDTO(int id, @NotBlank(message = "Expense category is required") String name,
			@NotBlank(message = "Expense category is required") String email,
			@NotBlank(message = "Expense category is required") String password, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", roles="
				+ roles + "]";
	}
}