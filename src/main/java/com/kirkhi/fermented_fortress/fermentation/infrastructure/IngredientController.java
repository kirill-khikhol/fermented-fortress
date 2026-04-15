package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirkhi.fermented_fortress.fermentation.application.IngredientService;
import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;

	public IngredientController(IngredientService ingredientService) {
		super();
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public ResponseEntity<List<IngredientDto>> getAll() {
		return ResponseEntity.ok(ingredientService.getAll());
	}

	@PostMapping
	public ResponseEntity<IngredientDto> create(@RequestBody IngredientDto ingredientDto) {
		return ResponseEntity.ok(ingredientService.create(ingredientDto));
	}

	@DeleteMapping("/id")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		ingredientService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/id")
	public ResponseEntity<IngredientDto> update(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {
		return ResponseEntity.ok(ingredientService.update(ingredientDto));
	}

	@GetMapping("/id")
	public ResponseEntity<IngredientDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(ingredientService.findById(id));
	}

}
