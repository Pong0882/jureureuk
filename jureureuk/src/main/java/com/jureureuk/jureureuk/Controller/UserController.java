package com.jureureuk.jureureuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jureureuk.jureureuk.entity.User;
import com.jureureuk.jureureuk.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/nickname")
    public String nicknamePage(OAuth2AuthenticationToken authentication, Model model) {
        if (authentication != null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");

            model.addAttribute("googleId", email != null ? email : "Unknown");
            model.addAttribute("name", name != null ? name : "Unknown");

            // 데이터베이스에서 이메일 확인
            User user = userService.findUserByGoogleId(email);
            if (user != null) {
                // 이메일이 존재하면 메인 페이지로 이동
                return "redirect:/mainPage";
            }

            // 로그인된 사용자 정보를 터미널에 출력
            System.out.println("로그인된 사용자 이메일: " + email);
            System.out.println("로그인된 사용자 이름: " + name);
        }
        return "nicknamePage"; // nicknamePage.html로 이동
    }

    @PostMapping("/nickname/save")
    public String saveNickname(@RequestParam("nickname") String nickname, OAuth2AuthenticationToken authentication) {
        // 사용자 정보를 가져와 구글 아이디를 가져옴
        OAuth2User oAuth2User = authentication.getPrincipal();
        String googleId = oAuth2User.getAttribute("email");

        // 데이터베이스에 닉네임과 구글 아이디 저장
        userService.saveNickname(googleId, nickname);

        System.out.println("로그인된 사용자 이메일: " + googleId);
        System.out.println("로그인된 사용자 이메일: " + nickname);

        return "redirect:/mainPage"; // 메인 페이지로 리다이렉트
    }

}
