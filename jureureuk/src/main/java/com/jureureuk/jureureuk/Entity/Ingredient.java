package com.jureureuk.jureureuk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 번호

    @Column(nullable = true)
    private String name; // 재료 이름

    @Column(nullable = true)
    private String photo; // 사진 경로

    @Column(nullable = true)
    private String description; // 설명

    @Column(nullable = true)
    private Integer proof; // 도수

    @Column(nullable = true)
    private String type; // 재료 종류 (예: base, liqueur)

    @Column(nullable = true)
    private String nameEn; // 재료 영어 이름
    // toString 메서드 추가 (디버깅용)

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
