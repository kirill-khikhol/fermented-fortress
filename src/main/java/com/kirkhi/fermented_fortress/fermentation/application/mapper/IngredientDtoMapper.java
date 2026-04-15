package com.kirkhi.fermented_fortress.fermentation.application.mapper;

import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

public class IngredientDtoMapper {

	public static Ingredient toDomain(IngredientDto dto) {
		Ingredient ingredient = Ingredient.create(dto.getName(), 
				Unit.valueOf(dto.getDefaultUnit()), 
				IngredientType.valueOf(dto.getIngredientType()), 
				dto.getNotes()).getValue();
		return ingredient;
	}
	
	public static IngredientDto toDto(Ingredient ingredient) {
		IngredientDto dto = new IngredientDto();
		dto.setName(ingredient.getName());
		dto.setDefaultUnit(ingredient.getDefaultUnit().name());
		dto.setIngredientType(ingredient.getIngredientType().name());
		dto.setNotes(ingredient.getNotes());
		return dto;
	}
	
	
}
