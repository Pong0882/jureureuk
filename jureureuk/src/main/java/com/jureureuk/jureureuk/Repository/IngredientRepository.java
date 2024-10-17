package com.jureureuk.jureureuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jureureuk.jureureuk.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
