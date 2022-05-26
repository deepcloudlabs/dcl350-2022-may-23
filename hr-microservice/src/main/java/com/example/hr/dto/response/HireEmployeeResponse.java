package com.example.hr.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 	
public class HireEmployeeResponse {

	private final String status;
	private final String reason;

	public HireEmployeeResponse(String status) {
		this.status = status;
		this.reason = null;
	}

	public HireEmployeeResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

}
