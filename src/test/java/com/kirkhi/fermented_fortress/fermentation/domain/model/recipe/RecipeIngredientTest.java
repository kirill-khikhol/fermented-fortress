package com.kirkhi.fermented_fortress.fermentation.domain.model.recipe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;

class RecipeIngredientTest {

    @Test
    void createSuccess() {
        Result<Ingredient> ingrRes = Ingredient.create(null, "Salt", Unit.GRAM, IngredientType.SOLID, "table salt");
        assertTrue(ingrRes.isSuccess());
        Ingredient ingredient = ingrRes.getValue();

        Result<RecipeIngredient> res = RecipeIngredient.create(10f, Unit.GRAM, "finely ground", false, ingredient);
        assertTrue(res.isSuccess(), "Result should be success for valid recipe ingredient");
        RecipeIngredient ri = res.getValue();
        assertNotNull(ri);
        assertEquals(10f, ri.getQuantity());
        assertEquals(Unit.GRAM, ri.getUnit());
        assertEquals("finely ground", ri.getPreparationNotes());
        assertFalse(ri.isOptional());
        assertEquals(ingredient, ri.getIngredient());
    }

    @Test
    void createFailsWhenQuantityNonPositive() {
        Result<Ingredient> ingrRes = Ingredient.create(null, "Salt", Unit.GRAM, IngredientType.SOLID, null);
        Ingredient ingredient = ingrRes.getValue();

        Result<RecipeIngredient> res = RecipeIngredient.create(0f, Unit.GRAM, null, false, ingredient);
        assertFalse(res.isSuccess());
        assertNotNull(res.getDomainError());
        assertTrue(res.getDomainError() instanceof ValidationError);
        assertEquals("Quantity must be greater than 0", res.getDomainError().getMessage());
    }

    @Test
    void createFailsWhenUnitNull() {
        Result<Ingredient> ingrRes = Ingredient.create(null, "Salt", Unit.GRAM, IngredientType.SOLID, null);
        Ingredient ingredient = ingrRes.getValue();

        Result<RecipeIngredient> res = RecipeIngredient.create(1f, null, null, false, ingredient);
        assertFalse(res.isSuccess());
        assertNotNull(res.getDomainError());
        assertTrue(res.getDomainError() instanceof ValidationError);
        assertEquals("Unit is required", res.getDomainError().getMessage());
    }

    @Test
    void createFailsWhenIngredientNull() {
        Result<RecipeIngredient> res = RecipeIngredient.create(1f, Unit.GRAM, null, false, null);
        assertFalse(res.isSuccess());
        assertNotNull(res.getDomainError());
        assertTrue(res.getDomainError() instanceof ValidationError);
        assertEquals("Ingredient is required", res.getDomainError().getMessage());
    }

}
