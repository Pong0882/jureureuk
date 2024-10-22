package com.jureureuk.jureureuk.repository;

import java.time.LocalDateTime;
// 아니 이거 왜 이렇게 되는걸까.. 일단 아래있는거지우고 5번줄에있는거 임포트 해야함 ㅇㅇ
// import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    @Query("SELECT c FROM Cocktail c WHERE c.recipeType = 1 ORDER BY c.likesCount DESC")
    List<Cocktail> findTop10ByLikesCount(); // 인기탑10

    @Query("SELECT c FROM Cocktail c WHERE c.recipeType = 2 ORDER BY c.likesCount DESC")
    List<Cocktail> findRandomTop10ByLikesCount(); // 랜덤탑10

    @Query("SELECT c FROM Cocktail c WHERE c.recipeType = 1 ORDER BY c.likesCount DESC")
    List<Cocktail> findAllPopularCocktailsByLikesCount(); // 인기레시피 좋아요순

    // recipe_type이 2인 칵테일 중에서 1주일 내 등록된 칵테일만 조회
    @Query("SELECT c FROM Cocktail c WHERE c.recipeType = 2 AND c.createdAt >= :startDate AND c.createdAt < :endDate")
    List<Cocktail> findCocktailsByWeekAndType(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // 좋아요 수 기준으로 상위 10개 칵테일을 조회하는 쿼리 메서드
    List<Cocktail> findTop10ByOrderByLikesCountDesc();

    // 만들수있는 칵테일
    @Query("SELECT c FROM Cocktail c WHERE c.id IN ("
            + "SELECT r.cocktail.id FROM Recipe r "
            + "JOIN UserIngredient ui ON r.ingredient.id = ui.ingredient.id "
            + "WHERE ui.googleId = :googleId "
            + "GROUP BY r.cocktail.id "
            + "HAVING COUNT(r.ingredient.id) = ("
            + "  SELECT COUNT(r2.ingredient.id) FROM Recipe r2 WHERE r2.cocktail.id = r.cocktail.id"
            + "))")
    List<Cocktail> findAvailableCocktailsByUserIngredients(@Param("googleId") String googleId);

    @Query(value = """
            SELECT c.id AS cocktail_id,
                   c.name AS cocktail_name,
                   c.photo AS cocktail_photo,  -- photo 필드 추가
                   i.name AS missing_ingredient
            FROM cocktail c
            JOIN recipe r ON c.id = r.cocktail_id
            LEFT JOIN user_ingredient ui
                   ON r.ingredient_id = ui.ingredient_id AND ui.google_id = :googleId
            JOIN ingredient i ON r.ingredient_id = i.id
            WHERE ui.ingredient_id IS NULL
              AND c.id IN (
                  SELECT r2.cocktail_id
                  FROM recipe r2
                  LEFT JOIN user_ingredient ui2
                         ON r2.ingredient_id = ui2.ingredient_id AND ui2.google_id = :googleId
                  GROUP BY r2.cocktail_id
                  HAVING COUNT(ui2.ingredient_id) = COUNT(r2.ingredient_id) - 1
              );
            """, nativeQuery = true)
    List<Object[]> findCocktailsWithOneMissingIngredient(@Param("googleId") String googleId);

    // 레시피 타입이 3인 칵테일을 조회하는 쿼리
    @Query("SELECT c FROM Cocktail c WHERE c.recipeType = :recipeType")
    List<Cocktail> findCocktailsByRecipeType(@Param("recipeType") int recipeType);

    
}