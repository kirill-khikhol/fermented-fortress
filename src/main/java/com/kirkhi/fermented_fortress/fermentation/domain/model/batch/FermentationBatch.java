package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kirkhi.fermented_fortress.common.application.DomainEvents;
import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.events.BatchLogAddedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationCompletedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationFailedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationStartedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.BatchStatus;

public class FermentationBatch {
	private Long id;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime expectedReadyTime;
	private BatchStatus status;
	private RecipeVariant recipeVariant;
	private List<BatchLog> batchLogs;

	private FermentationBatch(String name, RecipeVariant recipeVariant) {

		this.name = name;
		this.status = BatchStatus.PLANNED;
		this.recipeVariant = recipeVariant;
		this.batchLogs = new ArrayList<>();
	}

	public static Result<FermentationBatch> create(String name, RecipeVariant recipeVariant) {
		if (name == null || name.isBlank()) {
			return Result.failure(new ValidationError("name is required"));
		}
		if (recipeVariant == null) {
			return Result.failure(new ValidationError("recipeVariant is required"));
		}
		return Result.success(new FermentationBatch(name, recipeVariant));
	}

	public Result<Void> start() {
		if (this.status != BatchStatus.PLANNED) {
			return Result.failure(new ValidationError("Batch can only be started from PLANNED status."));
		}
		if (this.recipeVariant == null || this.recipeVariant.getBaseRecipe() == null
				|| this.recipeVariant.getBaseRecipe().getFermentationTime() == null) {
			return Result.failure(new ValidationError("Recipe and fermentation time are required to start a batch"));
		}

		this.status = BatchStatus.FERMENTING;
		this.startDate = LocalDateTime.now();
		this.expectedReadyTime = startDate.plus(recipeVariant.getBaseRecipe().getFermentationTime());
		DomainEvents.publish(new FermentationStartedEvent(this));
		return Result.success(null);
	}

	public Result<Void> markReady() {
		if (this.status != BatchStatus.FERMENTING) {
			return Result.failure(new ValidationError("Batch can only be completed from FERMENTING status."));
		}
		this.status = BatchStatus.READY;
		DomainEvents.publish(new FermentationCompletedEvent(this));
		return Result.success(null);
	}

	public Result<Void> markFailed() {
		if (this.status != BatchStatus.FERMENTING) {
			return Result.failure(new ValidationError("Batch can only be marked failed from FERMENTING status."));
		}
		this.status = BatchStatus.FAILED;
		DomainEvents.publish(new FermentationFailedEvent(this));
		return Result.success(null);
	}

	public Result<Void> addBatchLog(BatchLog log) {
		if (log == null) {
			return Result.failure(new ValidationError("log is required"));
		}
		if (this.batchLogs == null) {
			this.batchLogs = new ArrayList<>();
		}
		this.batchLogs.add(log);
		DomainEvents.publish(new BatchLogAddedEvent(this, log));
		return Result.success(null);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getExpectedReadyTime() {
		return expectedReadyTime;
	}

	public BatchStatus getStatus() {
		return status;
	}

	public RecipeVariant getRecipeVariant() {
		return recipeVariant;
	}

	public List<BatchLog> getBatchLogs() {
		return batchLogs;
	}

}
