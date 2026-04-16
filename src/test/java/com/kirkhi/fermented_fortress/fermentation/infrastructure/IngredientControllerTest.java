package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kirkhi.fermented_fortress.fermentation.application.IngredientService;
import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;

class IngredientControllerTest {

    private IngredientController controller;

    @BeforeEach
    void setup() {
        IngredientService fakeService = new IngredientService(null) {
            @Override
            public List<IngredientDto> getAll() {
                IngredientDto d = new IngredientDto();
                d.setName("Salt");
                d.setDefaultUnit("GRAM");
                d.setIngredientType("SOLID");
                return List.of(d);
            }

            @Override
            public IngredientDto create(IngredientDto ingredientDto) {
                return ingredientDto;
            }

            @Override
            public IngredientDto findById(Long id) {
                IngredientDto d = new IngredientDto();
                d.setName("Salt");
                d.setDefaultUnit("GRAM");
                d.setIngredientType("SOLID");
                return d;
            }

            @Override
            public IngredientDto update(IngredientDto ingredientDto) {
                return ingredientDto;
            }

            @Override
            public IngredientDto deleteById(Long id) {
                IngredientDto d = new IngredientDto();
                d.setName("Deleted");
                d.setDefaultUnit("GRAM");
                d.setIngredientType("SOLID");
                return d;
            }
        };

        controller = new IngredientController(fakeService);
    }

    @Test
    void getAllReturns200AndList() {
        ResponseEntity<List<IngredientDto>> resp = controller.getAll();
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals(1, resp.getBody().size());
    }

    @Test
    void createReturns200AndDto() {
        IngredientDto dto = new IngredientDto();
        dto.setName("New");
        ResponseEntity<IngredientDto> resp = controller.create(dto);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("New", resp.getBody().getName());
    }

    @Test
    void findByIdReturns200AndDto() {
        ResponseEntity<IngredientDto> resp = controller.findById(1L);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("Salt", resp.getBody().getName());
    }

    @Test
    void updateReturns200AndDto() {
        IngredientDto dto = new IngredientDto();
        dto.setName("Updated");
        ResponseEntity<IngredientDto> resp = controller.update(1L, dto);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("Updated", resp.getBody().getName());
    }

    @Test
    void deleteByIdReturns200() {
        ResponseEntity<Void> resp = controller.deleteById(1L);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
