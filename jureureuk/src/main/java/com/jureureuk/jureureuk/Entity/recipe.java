package com.jureureuk.jureureuk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 설정
    private Long id;  // 식별자 필드 추가

    @ManyToOne
    @JoinColumn(name = "cocktail_id")  // 외래 키: Cocktail
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")  // 외래 키: Ingredient
    private Ingredient ingredient;

    private String quantity;  // 예: '50ml', '2 pieces'
}
