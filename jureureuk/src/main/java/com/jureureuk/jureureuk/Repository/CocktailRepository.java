package com.jureureuk.jureureuk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.Entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
}
