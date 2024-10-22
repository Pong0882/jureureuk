package com.jureureuk.jureureuk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jureureuk.jureureuk.entity.Ingredient;
import com.jureureuk.jureureuk.entity.UserIngredient;
import com.jureureuk.jureureuk.repository.IngredientRepository;
import com.jureureuk.jureureuk.repository.UserIngredientRepository;

@Service
public class UserIngredientService {

    private final UserIngredientRepository userIngredientRepository;
    private final IngredientRepository ingredientRepository;

    // 생성자 주입 방식
    @Autowired
    public UserIngredientService(UserIngredientRepository userIngredientRepository,
            IngredientRepository ingredientRepository) {
        this.userIngredientRepository = userIngredientRepository;
        this.ingredientRepository = ingredientRepository;
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
        try {
            userIngredientRepository.deleteByGoogleIdAndIngredientId(googleId, ingredientId);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 출력
        }
    }

    public void saveUserIngredient(String googleId, Ingredient ingredient) {
        UserIngredient userIngredient = new UserIngredient(googleId, ingredient);
        userIngredientRepository.save(userIngredient); // 저장
    }

    // 사용자의 모든 재료 조회
    public List<UserIngredient> findIngredientsByUser2(String googleId) {
        return userIngredientRepository.findByGoogleId(googleId);
    }

    public List<Ingredient> getUserIngredientsByType(String googleId, String type) {
        // 해당 사용자의 재료 ID 목록 조회
        List<Long> ingredientIds = userIngredientRepository.findIngredientIdsByGoogleId(googleId);

        // type이 "all"이면 전체 재료 반환
        if (type.equalsIgnoreCase("all")) {
            return ingredientRepository.findByIdIn(ingredientIds);
        }
        // 특정 타입으로 필터링된 재료 반환
        else {
            return ingredientRepository.findByIdInAndType(ingredientIds, type);
        }
    }

}