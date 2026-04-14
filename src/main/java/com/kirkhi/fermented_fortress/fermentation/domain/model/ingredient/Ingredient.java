package com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient;

import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.exception.DomainValidationException;


public class Ingredient {

	private Long id;
	private String name;
	private Unit defaultUnit;
	private IngredientType ingredientType;
	private String notes;

	public Ingredient(Long id, String name, Unit defaultUnit, IngredientType ingredientType, String notes) {
		if (name == null || name.isBlank()) {
			throw new DomainValidationException("Ingredient name is required");
		}
		this.id = id;
		this.name = name;
		this.defaultUnit = defaultUnit;
		this.ingredientType = ingredientType;
		this.notes = notes;
	}

	public Ingredient(String name, Unit defaultUnit, IngredientType ingredientType, String notes) {
		this(null, name, defaultUnit, ingredientType, notes);
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
			throw new DomainValidationException("Ingredient name is required");
		}
		this.name = name;
	}

	public Unit getDefaultUnit() {
		return defaultUnit;
	}

	public void setDefaultUnit(Unit defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
