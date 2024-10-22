package com.jureureuk.jureureuk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT c FROM Cocktail c JOIN Recipe r ON c.id = r.cocktail.id WHERE r.ingredient.id = :ingredientId")
    List<Cocktail> findCocktailsByIngredient(@Param("ingredientId") Long ingredientId);

    // 특정 ID 목록에 해당하는 재료 찾기
    List<Ingredient> findByIdIn(List<Long> ids);

    // 특정 타입의 ID 목록에 해당하는 재료 찾기
    @Query("SELECT i FROM Ingredient i WHERE i.id IN :ids AND i.type = :type")
    List<Ingredient> findByIdInAndType(@Param("ids") List<Long> ids, @Param("type") String type);

}
