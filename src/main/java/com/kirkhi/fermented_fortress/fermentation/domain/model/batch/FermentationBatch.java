package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.kirkhi.fermented_fortress.fermentation.domain.exception.DomainValidationException;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.BatchStatus;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationStartedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationCompletedEvent;
import com.kirkhi.fermented_fortress.fermentation.domain.events.FermentationFailedEvent;
import com.kirkhi.fermented_fortress.common.application.DomainEvents;
import com.kirkhi.fermented_fortress.fermentation.domain.events.BatchLogAddedEvent;

public class FermentationBatch {
	private Long id;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime expectedReadyTime;
	private BatchStatus status;
	private RecipeVariant recipeVariant;
	private List<BatchLog> batchLogs;

	public FermentationBatch(String name, RecipeVariant recipeVariant) {
		Objects.requireNonNull(name, "name is required");
		if (name.isBlank()) {
			throw new DomainValidationException("name is required");
		}
		Objects.requireNonNull(recipeVariant, "recipeVariant is required");

		this.name = name;
		this.status = BatchStatus.PLANNED;
		this.recipeVariant = recipeVariant;
		this.batchLogs = new ArrayList<>();
	}

	public void start() {
		if (this.status != BatchStatus.PLANNED) {
			throw new DomainValidationException("Batch can only be started from PLANNED status.");
		}
		if (this.recipeVariant == null || this.recipeVariant.getBaseRecipe() == null
				|| this.recipeVariant.getBaseRecipe().getFermentationTime() == null) {
			throw new DomainValidationException("Recipe and fermentation time are required to start a batch");
		}

		this.status = BatchStatus.FERMENTING;
		this.startDate = LocalDateTime.now();
		this.expectedReadyTime = startDate.plus(recipeVariant.getBaseRecipe().getFermentationTime());
		DomainEvents.publish(new FermentationStartedEvent(this));
	}

	public void markReady() {
		if (this.status != BatchStatus.FERMENTING) {
			throw new DomainValidationException("Batch can only be completed from FERMENTING status.");
		}
		this.status = BatchStatus.READY;
		DomainEvents.publish(new FermentationCompletedEvent(this));
	}

	public void markFailed() {
		if (this.status != BatchStatus.FERMENTING) {
			throw new DomainValidationException("Batch can only be marked failed from FERMENTING status.");
		}
		this.status = BatchStatus.FAILED;
		DomainEvents.publish(new FermentationFailedEvent(this));
	}

	public void addBatchLog(BatchLog log) {
		if (log == null) {
			throw new DomainValidationException("log is required");
		}
		if (this.batchLogs == null) {
			this.batchLogs = new ArrayList<>();
		}
		this.batchLogs.add(log);
		DomainEvents.publish(new BatchLogAddedEvent(this, log));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isBlank()) {
			throw new DomainValidationException("name is required");
		}
		this.name = name;
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
		if (this.batchLogs == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(this.batchLogs);
	}

}
