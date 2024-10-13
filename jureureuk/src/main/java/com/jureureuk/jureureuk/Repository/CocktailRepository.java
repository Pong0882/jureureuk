package com.jureureuk.jureureuk.repository;

// 아니 이거 왜 이렇게 되는걸까.. 일단 아래있는거지우고 5번줄에있는거 임포트 해야함 ㅇㅇ
// import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    @Query("SELECT c FROM Cocktail c ORDER BY c.likesCount DESC")
    List<Cocktail> findTop10ByLikesCount();
}
