package com.jureureuk.jureureuk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.entity.Ingredient;
import com.jureureuk.jureureuk.entity.UserIngredient;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Long> {

    // 이메일을 사용해 재료 조회
    @Query("SELECT i FROM Ingredient i JOIN UserIngredient ui ON i.id = ui.ingredient.id WHERE ui.googleId = :email")
    List<Ingredient> findIngredientsByEmail(@Param("email") String email);

    // 사용자가 보유한 재료를 제외한 모든 재료 조회
    @Query("SELECT i FROM Ingredient i WHERE i.id NOT IN " +
            "(SELECT ui.ingredient.id FROM UserIngredient ui WHERE ui.googleId = :email)")
    List<Ingredient> findMissingIngredientsByEmail(@Param("email") String email);

    @Modifying // 유저 재료 삭제
    @Query("DELETE FROM UserIngredient ui WHERE ui.googleId = :googleId AND ui.ingredient.id = :ingredientId")
    void deleteByGoogleIdAndIngredientId(@Param("googleId") String googleId, @Param("ingredientId") Long ingredientId);
}
