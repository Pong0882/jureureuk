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

}