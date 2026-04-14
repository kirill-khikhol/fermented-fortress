package com.kirkhi.fermented_fortress.common.domain;

public class Result<T> {
	private final T value;
	private final DomainError domainError;

	public Result(T value, DomainError domainError) {
		super();
		this.value = value;
		this.domainError = domainError;
	}

	public static <T> Result<T> success(T value) {
		return new Result<>(value, null);
	}

	public static <T> Result<T> failure(DomainError domainError) {
		return new Result<>(null, domainError);
	}

	public boolean isSuccess() {
		return domainError == null;
	}

	public T getValue() {
		return value;
	}

	public DomainError getDomainError() {
		return domainError;
	}

}
