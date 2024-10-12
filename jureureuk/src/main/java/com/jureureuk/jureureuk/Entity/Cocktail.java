package com.jureureuk.jureureuk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cocktail")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktail_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String photo;

    // Getter 및 Setter
    public Long getCocktail_id() {
        return cocktail_id;
    }

    public void setCocktail_id(Long cocktail_id) {
        this.cocktail_id = cocktail_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
