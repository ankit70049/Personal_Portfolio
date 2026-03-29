package com.codinghub.portfolio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContactDto {
	
	@Size(min=2, max=30, message="Invalid Name Length")
	@NotBlank(message = "Name can't be empty")
	private String name;
	
	@Size(min=8, max=50, message="Inalid Email Length")
	@NotBlank(message="Email can't be empty")
	private String email;
	
	@Size(min=4, max=50, message="Inalid Subject Length")
	@NotBlank(message="Subject can't be empty")
	private String subject;
	
	@Size(min=4, max=1000, message="Inalid Message Length")
	@NotBlank(message="Message can't be empty")
	private String message;
	
	public ContactDto() {
		
	}
	
	public ContactDto(String name, String email, String subject, String message) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
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


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "ContactDto [name=" + name + ", email=" + email + ", subject=" + subject + ", message=" + message + "]";
	}
	
	
}
