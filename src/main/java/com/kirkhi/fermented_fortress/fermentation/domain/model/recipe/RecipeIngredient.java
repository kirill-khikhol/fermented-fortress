package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

public class RecipeIngredient {
	private Long id;
	private float quantity;
	private Unit unit;
	private String preparationNotes;
	private boolean optional;
	private Ingredient ingredient;

	public RecipeIngredient() {
	}

	public RecipeIngredient(Long id, float quantity, Unit unit, String preparationNotes, boolean optional, Ingredient ingredient) {
		this.id = id;
		this.quantity = quantity;
		this.unit = unit;
		this.preparationNotes = preparationNotes;
		this.optional = optional;
		this.ingredient = ingredient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getPreparationNotes() {
		return preparationNotes;
	}

	public void setPreparationNotes(String preparationNotes) {
		this.preparationNotes = preparationNotes;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
