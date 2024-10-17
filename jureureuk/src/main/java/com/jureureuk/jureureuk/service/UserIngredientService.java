package com.jureureuk.jureureuk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jureureuk.jureureuk.entity.Ingredient;
import com.jureureuk.jureureuk.entity.UserIngredient;
import com.jureureuk.jureureuk.repository.UserIngredientRepository;

@Service
public class UserIngredientService {
    private final UserIngredientRepository userIngredientRepository;

    public UserIngredientService(UserIngredientRepository userIngredientRepository) {
        this.userIngredientRepository = userIngredientRepository;
    }

    public List<Ingredient> findIngredientsByUser(String email) {
        List<Ingredient> ingredients = userIngredientRepository.findIngredientsByEmail(email);
        System.out.println("조회된 재료 목록: " + ingredients); // 디버깅용 출력
        return ingredients;
    }

    // 사용자가 보유하지 않은 재료 조회
    public List<Ingredient> findMissingIngredientsByEmail(String email) {
        List<Ingredient> ingredients = userIngredientRepository.findMissingIngredientsByEmail(email);
        System.out.println("조회된 재료 목록 (Service): " + ingredients);
        return ingredients;
    }

    public void deleteUserIngredient(String googleId, Long ingredientId) {
        // UserIngredient 엔티티를 찾아서 삭제하는 로직을 구현합니다.
        userIngredientRepository.deleteByGoogleIdAndIngredientId(googleId, ingredientId);
    }

    public void saveUserIngredient(String googleId, Ingredient ingredient) {
        UserIngredient userIngredient = new UserIngredient(googleId, ingredient);
        userIngredientRepository.save(userIngredient); // 저장
    }
}