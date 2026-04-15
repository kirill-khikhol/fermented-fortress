package com.kirkhi.fermented_fortress.fermentation.application.dto;

import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;

public class IngredientDto {
	private String name;
	private String defaultUnit;
	private String ingredientType;
	private String notes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefaultUnit() {
		return defaultUnit;
	}
	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}
	public String getIngredientType() {
		return ingredientType;
	}
	public void setIngredientType(String ingredientType) {
		this.ingredientType = ingredientType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
