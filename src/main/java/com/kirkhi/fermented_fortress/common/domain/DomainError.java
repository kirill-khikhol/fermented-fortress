package com.kirkhi.fermented_fortress.common.domain;

public class DomainError {
	private final String message;

	public DomainError(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
