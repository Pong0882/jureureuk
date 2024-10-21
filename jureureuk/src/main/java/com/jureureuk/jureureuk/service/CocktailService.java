package com.jureureuk.jureureuk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.repository.CocktailRepository;

@Service
public class CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;

    public List<Cocktail> getTop10CocktailsByLikes() { // 인기탑10
        return cocktailRepository.findTop10ByLikesCount();
    }

    public List<Cocktail> getTop10RandomCocktailsByLikes() { // 랜덤탑10
        return cocktailRepository.findRandomTop10ByLikesCount();
    }

    // 칵테일 정보 저장
    public Cocktail saveCocktail(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }

    // 모든 칵테일 가져오기
    public List<Cocktail> findAllCocktails() {
        return cocktailRepository.findAll();
    }

    // 모든 인기 칵테일 가져오기
    public List<Cocktail> findAllPopularCocktailsByLikes() {
        return cocktailRepository.findAllPopularCocktailsByLikesCount();
    }

    // 특정 ID의 칵테일 가져오기
    public Cocktail getCocktailById(Long i) {
        return cocktailRepository.findById(i).orElse(null);
    }

    // 칵테일 삭제
    public void deleteCocktail(Long id) {
        // cocktailRepository.deleteById(id);
    }

    // recipe_type이 2인 칵테일 중 최근 1주일 내에 등록된 것만 조회
    public List<Cocktail> getRecentType2Cocktails() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusWeeks(1);

        return cocktailRepository.findCocktailsByWeekAndType(startDate, endDate);
    }

    // 좋아요 수 기준 상위 10개 칵테일을 조회하는 메서드
    public List<Cocktail> getTop10Cocktails() {
        return cocktailRepository.findTop10ByOrderByLikesCountDesc();
    }

    // 좋아요 수 증가
    public void incrementLikeCount(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cocktail ID: " + id));
        cocktail.setLikesCount(cocktail.getLikesCount() + 1);
        cocktailRepository.save(cocktail);
    }

    // 즐겨찾기 수 증가
    public void incrementBookmarkCount(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cocktail ID: " + id));
        cocktail.setBookmarkCount(cocktail.getBookmarkCount() + 1);
        cocktailRepository.save(cocktail);
    }

}
