package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kirkhi.fermented_fortress.fermentation.domain.IIngredientRepository;
import com.kirkhi.fermented_fortress.fermentation.domain.model.ingredient.Ingredient;
import com.kirkhi.fermented_fortress.fermentation.infrastructure.mapper.IngredientEntityMapper;

@Repository
public class IngredientRepository implements IIngredientRepository {

	JpaIngredientRepository jpaIngredientRepository;

	public IngredientRepository(JpaIngredientRepository jpaIngredientRepository) {
		super();
		this.jpaIngredientRepository = jpaIngredientRepository;
	}

	@Override
	public List<Ingredient> findAll() {
		return jpaIngredientRepository.findAll().stream().map(IngredientEntityMapper::toDomain).toList();
	}

	@Override
	public Ingredient create(Ingredient ingredient) {
		return IngredientEntityMapper
				.toDomain(jpaIngredientRepository.save(IngredientEntityMapper.toEntity(ingredient)));
	}

	@Override
	public Ingredient findById(Long id) {
		return IngredientEntityMapper.toDomain(jpaIngredientRepository.findById(id).orElseThrow());
	}

	@Override
	public void deleteById(Long id) {
		jpaIngredientRepository.deleteById(id);
	}

	@Override
	public Ingredient update(Ingredient ingredient) {
		return IngredientEntityMapper
				.toDomain(jpaIngredientRepository.save(IngredientEntityMapper.toEntity(ingredient)));
	}

}
