package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

public class RecipeIngredient {
	private Long id;
	private float quantity;
	private Unit unit;
	private String preparationNotes;
	private boolean optional;
	private Ingredient ingredient;

	private RecipeIngredient(Long id, float quantity, Unit unit, String preparationNotes, boolean optional,
			Ingredient ingredient) {
		this.id = id;
		this.quantity = quantity;
		this.unit = unit;
		this.preparationNotes = preparationNotes;
		this.optional = optional;
		this.ingredient = ingredient;
	}

	public static Result<RecipeIngredient> create(float quantity, Unit unit, String preparationNotes, boolean optional,
			Ingredient ingredient) {
		RecipeIngredient recipeIngredient = new RecipeIngredient(null, quantity, unit, preparationNotes, optional,
				ingredient);
		return Result.success(recipeIngredient);
	}

	public Long getId() {
		return id;
	}

	public float getQuantity() {
		return quantity;
	}

	public Unit getUnit() {
		return unit;
	}

	public String getPreparationNotes() {
		return preparationNotes;
	}

	public boolean isOptional() {
		return optional;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	
}
