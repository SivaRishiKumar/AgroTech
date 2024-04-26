package com.jsp.agro_springBoot.util;

public class ResponseStructure<T> {

	private int status;
	private String message;
	private T details;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getDetails() {
		return details;
	}
	public void setDetails(T details) {
		this.details = details;
	}
	public ResponseStructure(int status, String message, T details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}
	public ResponseStructure() {
		super();
	}
	
	
	
	
}
