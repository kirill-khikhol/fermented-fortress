package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirkhi.fermented_fortress.fermentation.infrastructure.entities.IngredientEntity;

public interface JpaIngredientRepository extends JpaRepository<IngredientEntity, Long> {

}
