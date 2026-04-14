package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import java.time.temporal.TemporalAmount;
import java.util.List;

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

	public void addIngredient(RecipeIngredient recipeIngredient) {
		this.ingredients.add(recipeIngredient);
	}
	
	public void removeIngredient(RecipeIngredient recipeIngredient) {
		this.ingredients.remove(recipeIngredient);
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
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TemporalAmount getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(TemporalAmount preparationTime) {
		this.preparationTime = preparationTime;
	}

	public TemporalAmount getFermentationTime() {
		return fermentationTime;
	}

	public void setFermentationTime(TemporalAmount fermentationTime) {
		this.fermentationTime = fermentationTime;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Ingredient getBaseIngredient() {
		return baseIngredient;
	}

	public void setBaseIngredient(Ingredient baseIngredient) {
		this.baseIngredient = baseIngredient;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

}
