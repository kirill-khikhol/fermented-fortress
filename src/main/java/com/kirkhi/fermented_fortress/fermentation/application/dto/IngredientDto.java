package com.kirkhi.fermented_fortress.fermentation.application.dto;

public class IngredientDto {
	private Long id;
	private String name;
	private String defaultUnit;
	private String ingredientType;
	private String notes;

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
