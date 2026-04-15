package com.kirkhi.fermented_fortress.fermentation.domain;

import java.util.List;

import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

public interface IIngredientRepository {

	List<Ingredient> findAll();

	Ingredient create(Ingredient ingredient);

	Ingredient findById(Long id);

	void deleteById(Long id);

	Ingredient update(Ingredient ingredient);
}
