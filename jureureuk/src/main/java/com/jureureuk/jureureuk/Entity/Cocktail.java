package com.jureureuk.jureureuk.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 번호

    @Column(nullable = true)
    private String name; // 칵테일 이름

    @Column(nullable = true)
    private String photo; // 칵테일 사진 URL

    @Column(nullable = true)
    private String description; // 칵테일 설명

    @Column(nullable = true)
    private Integer likesCount; // 좋아요 수

    @Column(nullable = true)
    private Integer dislikesCount; // 싫어요 수

    @Column(nullable = true)
    private Integer bookmarkCount; // 즐겨찾기 수

    @Column(nullable = true)
    private String recipe1; // 레시피 1

    @Column(nullable = true)
    private String recipe2; // 레시피 2

    @Column(nullable = true)
    private String googleId; // 구글 아이디

    @Column(nullable = true)
    private Integer recipeType; // 레시피타입

    @Column(name = "created_at", updatable = false) // 등록 시간 필드 추가
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 등록 시간 자동 기록
    }
}
