// UserRepository.java
package com.jureureuk.jureureuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jureureuk.jureureuk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Google ID로 유저 찾기 위한 메소드 정의
    User findByGoogleId(String googleId);
}