package com.kirkhi.fermented_fortress.fermentation.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;
import com.kirkhi.fermented_fortress.fermentation.application.mapper.IngredientDtoMapper;
import com.kirkhi.fermented_fortress.fermentation.domain.IIngredientRepository;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;

@Service
public class IngredientService {

	private final IIngredientRepository ingredientRepository;

	public IngredientService(IIngredientRepository ingredientRepository) {
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

	public IngredientDto update(Long id, IngredientDto ingredientDto) {
		Ingredient existingIngredient = ingredientRepository.findById(id);
		Ingredient ingredient = Ingredient.create(existingIngredient.getId(),
				ingredientDto.getName() != null ? ingredientDto.getName() : existingIngredient.getName(),
				ingredientDto.getDefaultUnit() != null ? Unit.valueOf(ingredientDto.getDefaultUnit())
						: existingIngredient.getDefaultUnit(),
				ingredientDto.getIngredientType() != null ? IngredientType.valueOf(ingredientDto.getIngredientType())
						: existingIngredient.getIngredientType(),
				ingredientDto.getNotes() != null ? ingredientDto.getNotes() : existingIngredient.getNotes()).getValue();

		return IngredientDtoMapper.toDto(ingredientRepository.update(ingredient));
	}

	public IngredientDto deleteById(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id);
		ingredientRepository.deleteById(id);
		return IngredientDtoMapper.toDto(ingredient);
	}
}
