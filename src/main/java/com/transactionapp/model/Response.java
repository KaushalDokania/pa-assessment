package com.transactionapp.model;

import java.io.Serializable;

public class Response implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String status;

	public Response(String status) {
		this.status = status;
	}

	/* ------- Getters & Setters ------- */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
