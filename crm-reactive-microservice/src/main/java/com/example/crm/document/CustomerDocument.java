package com.example.crm.document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="customers")
@Data
public class CustomerDocument {
	@Id
	private String identity;
	@Size(min=5)
	private String fullName;
	@Email
	private String email;
	private String phone;
}
