package com.kirkhi.fermented_fortress.fermentation.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;
import com.kirkhi.fermented_fortress.fermentation.application.mapper.IngredientDtoMapper;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;
import com.kirkhi.fermented_fortress.fermentation.infrastructure.IngredientRepository;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
	}

	public List<IngredientDto> getAll() {
		return ingredientRepository.findAll().stream().map(IngredientDtoMapper::toDto).toList();
	}

	public IngredientDto create(IngredientDto ingredientDto) {
		Ingredient ingredient = IngredientDtoMapper.toDomain(ingredientDto);
		return IngredientDtoMapper.toDto(ingredientRepository.create(ingredient));
	}

	public IngredientDto findById(Long id) {
		return IngredientDtoMapper.toDto(ingredientRepository.findById(id));
	}

	public IngredientDto update(IngredientDto ingredientDto) {
		Ingredient ingredient = IngredientDtoMapper.toDomain(ingredientDto);
		return IngredientDtoMapper.toDto(ingredientRepository.update(ingredient));
	}

	public IngredientDto deleteById(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id);
		ingredientRepository.deleteById(id);
		return IngredientDtoMapper.toDto(ingredient);
	}
}
