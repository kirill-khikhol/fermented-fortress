package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import java.time.LocalDate;

import com.kirkhi.fermented_fortress.fermentation.domain.exception.DomainValidationException;

public class BatchLog {
	private Long id;
	private LocalDate logDate;
	private int dayNumber;
	private String actionTaken;

	public BatchLog(Long id, LocalDate logDate, int dayNumber, String actionTaken) {
		if (logDate == null) {
			throw new DomainValidationException("logDate is required");
		}
		if (dayNumber < 0) {
			throw new DomainValidationException("dayNumber must be >= 0");
		}
		if (actionTaken == null || actionTaken.isBlank()) {
			throw new DomainValidationException("actionTaken is required");
		}
		this.id = id;
		this.logDate = logDate;
		this.dayNumber = dayNumber;
		this.actionTaken = actionTaken;
	}

	public BatchLog(LocalDate logDate, int dayNumber, String actionTaken) {
		this(null, logDate, dayNumber, actionTaken);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDate logDate) {
		if (logDate == null) {
			throw new DomainValidationException("logDate is required");
		}
		this.logDate = logDate;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		if (dayNumber < 0) {
			throw new DomainValidationException("dayNumber must be >= 0");
		}
		this.dayNumber = dayNumber;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		if (actionTaken == null || actionTaken.isBlank()) {
			throw new DomainValidationException("actionTaken is required");
		}
		this.actionTaken = actionTaken;
	}
}
