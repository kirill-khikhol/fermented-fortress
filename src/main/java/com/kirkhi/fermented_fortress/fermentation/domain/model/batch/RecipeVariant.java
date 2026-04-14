package com.kirkhi.fermented_fortress.fermentation.domain.model.batch;

import com.kirkhi.fermented_fortress.fermentation.domain.model.recipe.Recipe;

public class RecipeVariant extends Recipe {

	private Recipe baseRecipe;

	public Recipe getBaseRecipe() {
		return baseRecipe;
	}

	public void setBaseRecipe(Recipe baseRecipe) {
		this.baseRecipe = baseRecipe;
	}

}
