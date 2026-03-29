package com.codinghub.portfolio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ServiceDto {
	
	@Size(min=2, max=30, message="Invalid Title Length")
	@NotBlank(message = "Title can't be empty")
	private String title;
	
	@Size(min=8, max=80, message="Inalid Description Length")
	@NotBlank(message="Description can't be empty")
	private String description;
	
	private MultipartFile   serviceFile;
	
	
	
	
	public ServiceDto() {
		
	}

	public ServiceDto(String title,String description,MultipartFile serviceFile) {
		super();
		this.title = title;
		this.description = description;
		this.serviceFile = serviceFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getServiceFile() {
		return serviceFile;
	}

	public void setServiceFile(MultipartFile serviceFile) {
		this.serviceFile = serviceFile;
	}

	@Override
	public String toString() {
		return "ServiceDto [title=" + title + ", description=" + description + ", serviceFile=" + serviceFile + "]";
	}

	
	
	
}
