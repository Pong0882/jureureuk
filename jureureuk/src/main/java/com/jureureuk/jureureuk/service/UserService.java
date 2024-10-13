// UserService.java
package com.jureureuk.jureureuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jureureuk.jureureuk.entity.User;
import com.jureureuk.jureureuk.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Google ID로 유저 찾기
    public User findUserByGoogleId(String googleId) {
        System.out.println("Google ID로 사용자 검색: " + googleId);
        return userRepository.findByGoogleId(googleId);
    }

    // DB에 유저 저장하기
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveNickname(String googleId, String nickname) {
        User user = new User();
        user.setGoogleId(googleId);
        user.setNickname(nickname);
        userRepository.save(user);
    }
}
