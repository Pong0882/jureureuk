package com.jureureuk.jureureuk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.entity.Ingredient;
import com.jureureuk.jureureuk.repository.IngredientRepository;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    // 재료 ID로 칵테일 목록 가져오는 메서드
    public List<Cocktail> getCocktailsByIngredient(Long ingredientId) {
        return ingredientRepository.findCocktailsByIngredient(ingredientId);
    }
}
