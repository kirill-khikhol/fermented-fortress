package com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.exception.DomainValidationException;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;

public class Ingredient {

	private Long id;
	private String name;
	private Unit defaultUnit;
	private IngredientType ingredientType;
	private String notes;

	private Ingredient(Long id, String name, Unit defaultUnit, IngredientType ingredientType, String notes) {
		this.id = id;
		this.name = name;
		this.defaultUnit = defaultUnit;
		this.ingredientType = ingredientType;
		this.notes = notes;
	}

	public static Result<Ingredient> create(String name, Unit defaultUnit, IngredientType ingredientType,
			String notes) {
		if (name == null || name.isBlank()) {
			return Result.failure(new ValidationError("Ingredient name is required"));
		}
		Ingredient ingredient = new Ingredient(null, name, defaultUnit, ingredientType, notes);
		return Result.success(ingredient);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Unit getDefaultUnit() {
		return defaultUnit;
	}

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public String getNotes() {
		return notes;
	}

}
