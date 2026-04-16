package com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kirkhi.fermented_fortress.common.domain.Result;
import com.kirkhi.fermented_fortress.common.domain.ValidationError;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;

class IngredientTest {

    @Test
    void createSuccess() {
        Result<Ingredient> result = Ingredient.create("Salt", Unit.GRAM, IngredientType.SOLID, "table salt");

        assertTrue(result.isSuccess(), "Result should be success for valid ingredient");
        Ingredient ingredient = result.getValue();
        assertNotNull(ingredient);
        assertNull(ingredient.getId(), "New ingredient should have null id");
        assertEquals("Salt", ingredient.getName());
        assertEquals(Unit.GRAM, ingredient.getDefaultUnit());
        assertEquals(IngredientType.SOLID, ingredient.getIngredientType());
        assertEquals("table salt", ingredient.getNotes());
    }

    @Test
    void createFailsWhenNameIsNull() {
        Result<Ingredient> result = Ingredient.create(null, Unit.LITER, IngredientType.LIQUID, null);

        assertFalse(result.isSuccess(), "Result should be failure when name is null");
        assertNotNull(result.getDomainError());
        assertTrue(result.getDomainError() instanceof ValidationError);
        assertEquals("Ingredient name is required", result.getDomainError().getMessage());
    }

    @Test
    void createFailsWhenNameIsBlank() {
        Result<Ingredient> result = Ingredient.create("   ", Unit.MILLILITER, IngredientType.LIQUID, "notes");

        assertFalse(result.isSuccess(), "Result should be failure when name is blank");
        assertNotNull(result.getDomainError());
        assertTrue(result.getDomainError() instanceof ValidationError);
        assertEquals("Ingredient name is required", result.getDomainError().getMessage());
    }

}
