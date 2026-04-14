package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import java.time.temporal.TemporalAmount;
import java.util.List;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;
import com.kirkhi.fermented_fortress.fermentation.domain.model.recipe.Recipe;
import com.kirkhi.fermented_fortress.fermentation.domain.model.recipe.RecipeIngredient;

public class RecipeVariant extends Recipe {

	private Recipe baseRecipe;

	private RecipeVariant(Long id, String name, String description, TemporalAmount preparationTime,
			TemporalAmount fermentationTime, int servings, String instructions, Ingredient baseIngredient,
			List<RecipeIngredient> ingredients, Recipe baseRecipe) {
		super(id, name, description, preparationTime, fermentationTime, servings, instructions, baseIngredient,
				ingredients);
		this.baseRecipe = baseRecipe;
	}

	public static Result<RecipeVariant> create(String name, String description, TemporalAmount preparationTime,
			TemporalAmount fermentationTime, int servings, String instructions, Ingredient baseIngredient,
			List<RecipeIngredient> ingredients, Recipe baseRecipe) {
		RecipeVariant recipeVariant = new RecipeVariant(null, name, description, preparationTime, fermentationTime,
				servings, instructions, baseIngredient, ingredients, baseRecipe);
		return Result.success(recipeVariant);
	}

	public Recipe getBaseRecipe() {
		return baseRecipe;
	}

}
