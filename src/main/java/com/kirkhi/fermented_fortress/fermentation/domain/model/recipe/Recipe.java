package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import java.time.temporal.TemporalAmount;
import java.util.List;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

public class Recipe {
	private Long id;
	private String name;
	private String description;
	private TemporalAmount preparationTime;
	private TemporalAmount fermentationTime;
	private int servings;
	private String instructions;
	private Ingredient baseIngredient;
	private List<RecipeIngredient> ingredients;

	protected Recipe(Long id, String name, String description, TemporalAmount preparationTime,
			TemporalAmount fermentationTime, int servings, String instructions, Ingredient baseIngredient,
			List<RecipeIngredient> ingredients) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.preparationTime = preparationTime;
		this.fermentationTime = fermentationTime;
		this.servings = servings;
		this.instructions = instructions;
		this.baseIngredient = baseIngredient;
		this.ingredients = ingredients;
	}

	public static Result<Recipe> create(String name, String description, TemporalAmount preparationTime,
			TemporalAmount fermentationTime, int servings, String instructions, Ingredient baseIngredient,
			List<RecipeIngredient> ingredients) {
		if (name == null || name.isBlank()) {
			return Result.failure(new ValidationError("Recipe name is required"));
		}
		Recipe recipe = new Recipe(null, name, description, preparationTime, fermentationTime, servings, instructions,
				baseIngredient, ingredients);
		return Result.success(recipe);
	}

	public Result<Void> addIngredient(RecipeIngredient recipeIngredient) {
		this.ingredients.add(recipeIngredient);
		return Result.success(null);
	}

	public Result<Void> removeIngredient(RecipeIngredient recipeIngredient) {
		this.ingredients.remove(recipeIngredient);
		return Result.success(null);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public TemporalAmount getPreparationTime() {
		return preparationTime;
	}

	public TemporalAmount getFermentationTime() {
		return fermentationTime;
	}

	public int getServings() {
		return servings;
	}

	public String getInstructions() {
		return instructions;
	}

	public Ingredient getBaseIngredient() {
		return baseIngredient;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

}
