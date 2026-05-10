package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

class RecipeTest {

	@Test
	void createSuccess() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, "base");
		assertTrue(baseRes.isSuccess());
		Ingredient base = baseRes.getValue();

		Result<Ingredient> ingrRes = Ingredient.create(null, "Salt", Unit.GRAM, IngredientType.SOLID, "seasoning");
		assertTrue(ingrRes.isSuccess());
		Ingredient salt = ingrRes.getValue();

		Result<RecipeIngredient> riResBase = RecipeIngredient.create(1f, Unit.KILOGRAM, null, false, base);
		assertTrue(riResBase.isSuccess());
		RecipeIngredient riBase = riResBase.getValue();

		Result<RecipeIngredient> riResSalt = RecipeIngredient.create(20f, Unit.GRAM, null, false, salt);
		assertTrue(riResSalt.isSuccess());
		RecipeIngredient riSalt = riResSalt.getValue();

		Result<Recipe> res = Recipe.create("Kimchi", "Korean fermented cabbage", Duration.ofHours(1),
				Duration.ofDays(3), 4, "Mix and ferment", base, List.of(riBase, riSalt));

		assertTrue(res.isSuccess());
		Recipe recipe = res.getValue();
		assertNotNull(recipe);
		assertEquals("Kimchi", recipe.getName());
		assertEquals(4, recipe.getServings());
		assertEquals(base, recipe.getBaseIngredient());
		assertEquals(2, recipe.getIngredients().size());
	}

	@Test
	void createFailsWhenNameBlank() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, null);
		Ingredient base = baseRes.getValue();

		Result<RecipeIngredient> riRes = RecipeIngredient.create(1f, Unit.GRAM, null, false, base);
		RecipeIngredient ri = riRes.getValue();

		Result<Recipe> res = Recipe.create("   ", null, Duration.ofHours(1), Duration.ofDays(1), 1, null, base,
				List.of(ri));

		assertFalse(res.isSuccess());
		assertTrue(res.getDomainError() instanceof ValidationError);
		assertEquals("Recipe name is required", res.getDomainError().getMessage());
	}

	@Test
	void createFailsWhenFermentationTimeNull() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, null);
		Ingredient base = baseRes.getValue();

		Result<RecipeIngredient> riRes = RecipeIngredient.create(1f, Unit.GRAM, null, false, base);
		RecipeIngredient ri = riRes.getValue();

		Result<Recipe> res = Recipe.create("Sauce", null, Duration.ofMinutes(10), null, 1, null, base, List.of(ri));
		assertFalse(res.isSuccess());
		assertTrue(res.getDomainError() instanceof ValidationError);
		assertEquals("Fermentation time is required", res.getDomainError().getMessage());
	}

	@Test
	void createFailsWhenBaseIngredientNull() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, null);
		Ingredient base = baseRes.getValue();

		Result<RecipeIngredient> riRes = RecipeIngredient.create(1f, Unit.GRAM, null, false, base);
		RecipeIngredient ri = riRes.getValue();

		Result<Recipe> res = Recipe.create("Sauce", null, Duration.ofMinutes(10), Duration.ofHours(1), 1, null, null,
				List.of(ri));
		assertFalse(res.isSuccess());
		assertTrue(res.getDomainError() instanceof ValidationError);
		assertEquals("Base ingredient is required", res.getDomainError().getMessage());
	}

	@Test
	void createFailsWhenIngredientsEmpty() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, null);
		Ingredient base = baseRes.getValue();

		Result<Recipe> res = Recipe.create("Sauce", null, Duration.ofMinutes(10), Duration.ofHours(1), 1, null, base,
				List.of());
		assertFalse(res.isSuccess());
		assertTrue(res.getDomainError() instanceof ValidationError);
		assertEquals("At least one ingredient is required", res.getDomainError().getMessage());
	}

	@Test
	void addAndRemoveIngredient() {
		Result<Ingredient> baseRes = Ingredient.create(null, "Cabbage", Unit.PIECE, IngredientType.SOLID, null);
		Ingredient base = baseRes.getValue();

		Result<Ingredient> saltRes = Ingredient.create(null, "Salt", Unit.GRAM, IngredientType.SOLID, null);
		Ingredient salt = saltRes.getValue();

		Result<RecipeIngredient> riResBase = RecipeIngredient.create(1f, Unit.KILOGRAM, null, false, base);
		RecipeIngredient riBase = riResBase.getValue();

		Result<RecipeIngredient> riResSalt = RecipeIngredient.create(20f, Unit.GRAM, null, false, salt);
		RecipeIngredient riSalt = riResSalt.getValue();

		Result<Recipe> res = Recipe.create("Kimchi", "Korean fermented cabbage", Duration.ofHours(1),
				Duration.ofDays(3), 4, "Mix and ferment", base, List.of(riBase));

		assertTrue(res.isSuccess());
		Recipe recipe = res.getValue();

		recipe.addIngredient(riSalt);
		assertEquals(2, recipe.getIngredients().size());

		recipe.removeIngredient(riBase);
		recipe.removeIngredient(riBase);
		assertEquals(1, recipe.getIngredients().size());
	}

}
