package com.kirkhi.fermented_fortress.fermentation.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

class IngredientServiceTest {

    private IngredientService service;

    @BeforeEach
    void setup() {
        // create a simple in-memory fake repository by subclassing the concrete repository
        com.kirkhi.fermented_fortress.fermentation.infrastructure.IngredientRepository fakeRepo =
                new com.kirkhi.fermented_fortress.fermentation.infrastructure.IngredientRepository(null) {

                    private final List<Ingredient> data = new ArrayList<>();

                    {
                        data.add(Ingredient.create("Salt", Unit.GRAM, IngredientType.SOLID, "table salt").getValue());
                        data.add(Ingredient.create("Water", Unit.LITER, IngredientType.LIQUID, null).getValue());
                    }

                    @Override
                    public List<Ingredient> findAll() {
                        return new ArrayList<>(data);
                    }

                    @Override
                    public Ingredient create(Ingredient ingredient) {
                        data.add(ingredient);
                        return ingredient;
                    }

                    @Override
                    public Ingredient findById(Long id) {
                        return data.get(0);
                    }

                    @Override
                    public void deleteById(Long id) {
                        if (!data.isEmpty()) data.remove(0);
                    }

                    @Override
                    public Ingredient update(Ingredient ingredient) {
                        if (!data.isEmpty()) data.set(0, ingredient);
                        return ingredient;
                    }
                };

        service = new IngredientService(fakeRepo);
    }

    @Test
    void getAllReturnsDtos() {
        List<IngredientDto> all = service.getAll();
        assertNotNull(all);
        assertEquals(2, all.size());
        assertEquals("Salt", all.get(0).getName());
    }

    @Test
    void createMapsAndReturnsDto() {
        IngredientDto dto = new IngredientDto();
        dto.setName("Pepper");
        dto.setDefaultUnit(Unit.GRAM.name());
        dto.setIngredientType(IngredientType.POWDER.name());
        dto.setNotes("black pepper");

        IngredientDto created = service.create(dto);
        assertNotNull(created);
        assertEquals("Pepper", created.getName());
    }

    @Test
    void findByIdReturnsDto() {
        IngredientDto found = service.findById(1L);
        assertNotNull(found);
        assertEquals("Salt", found.getName());
    }

    @Test
    void updateReturnsDto() {
        IngredientDto dto = new IngredientDto();
        dto.setName("Updated");
        dto.setDefaultUnit(Unit.GRAM.name());
        dto.setIngredientType(IngredientType.SOLID.name());
        dto.setNotes("u");

        IngredientDto updated = service.update(dto);
        assertNotNull(updated);
        assertEquals("Updated", updated.getName());
    }

    @Test
    void deleteByIdReturnsDeletedDto() {
        IngredientDto deleted = service.deleteById(1L);
        assertNotNull(deleted);
        assertEquals("Salt", deleted.getName());
    }
}
