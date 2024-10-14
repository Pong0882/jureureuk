package com.jureureuk.jureureuk.service;

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

    // 특정 ID의 칵테일 가져오기
    public Cocktail getCocktailById(int id) {
        return cocktailRepository.findById(id).orElse(null);
    }

    // 칵테일 삭제
    public void deleteCocktail(Long id) {
        // cocktailRepository.deleteById(id);
    }
}
