package com.kirkhi.fermented_fortress.fermentation.infrastructure.mapper;

import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;
import com.kirkhi.fermented_fortress.fermentation.infrastructure.entities.IngredientEntity;

public class IngredientEntityMapper {

	public static Ingredient toDomain(IngredientEntity entity) {
		Ingredient ingredient = Ingredient.create(entity.getId(), entity.getName(), entity.getDefaultUnit(),
				entity.getIngredientType(), entity.getNotes()).getValue();
		return ingredient;
	}

	public static IngredientEntity toEntity(Ingredient ingredient) {
		IngredientEntity entity = new IngredientEntity();
		entity.setId(ingredient.getId());
		entity.setName(ingredient.getName());
		entity.setDefaultUnit(ingredient.getDefaultUnit());
		entity.setIngredientType(ingredient.getIngredientType());
		entity.setNotes(ingredient.getNotes());
		return entity;
	}
}
