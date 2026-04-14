package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import java.time.LocalDate;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;

public class BatchLog {
	private Long id;
	private LocalDate logDate;
	private int dayNumber;
	private String actionTaken;

	private BatchLog(Long id, LocalDate logDate, int dayNumber, String actionTaken) {

		this.id = id;
		this.logDate = logDate;
		this.dayNumber = dayNumber;
		this.actionTaken = actionTaken;
	}
	
	public static Result<BatchLog> create(LocalDate logDate, int dayNumber, String actionTaken) {
		if (logDate == null) {
			return Result.failure(new ValidationError("logDate is required"));
		}
		if (dayNumber < 0) {
			return Result.failure(new ValidationError("dayNumber must be >= 0"));
		}
		if (actionTaken == null || actionTaken.isBlank()) {
			return Result.failure(new ValidationError("actionTaken is required"));
		}
		BatchLog bachLog = new BatchLog(null, logDate, dayNumber, actionTaken);
		
		return Result.success(bachLog);
	}

	public BatchLog(LocalDate logDate, int dayNumber, String actionTaken) {
		this(null, logDate, dayNumber, actionTaken);
	}

	public Long getId() {
		return id;
	}

	public LocalDate getLogDate() {
		return logDate;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	
}
