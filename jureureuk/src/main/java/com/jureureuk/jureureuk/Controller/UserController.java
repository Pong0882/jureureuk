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
            String name = oAuth2User.getAttribute("name"); // 구글 아이디 가져오기

            model.addAttribute("googleId", email != null ? email : "Unknown");
            model.addAttribute("name", name != null ? name : "Unknown");

            // 데이터베이스에서 이메일 확인
            User user = userService.findUserByGoogleId(email);

            System.out.println("로그인된 사용자 이메일: " + email);
            System.out.println("로그인된 사용자 이름: " + name);

            // 아래 코드 나중에 다시씀
            if (user != null) {
                // 이메일이 존재하면 메인 페이지로 이동
                return "redirect:/main";
            }

            // 로그인된 사용자 정보를 터미널에 출력
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

        return "redirect:/main"; // 메인 페이지로 리다이렉트
    }

    @GetMapping("/profile")
    public String profilePage(OAuth2AuthenticationToken authentication, Model model) {
        if (authentication != null) {
            // 인증된 사용자 정보를 가져옴
            OAuth2User oAuth2User = authentication.getPrincipal();
            String googleId = oAuth2User.getAttribute("email");

            // 데이터베이스에서 유저 정보 조회
            User user = userService.findUserByGoogleId(googleId);

            if (user != null) {
                // 유저 정보 모델에 추가
                model.addAttribute("user", user);

                System.out.println("로그인된 사용자 이메일: " + user.getGoogleId());
                System.out.println("로그인된 사용자 닉네임: " + user.getNickname());
            } else {
                // 유저 정보가 없을 때 기본 메시지 추가
                model.addAttribute("message", "사용자 정보를 찾을 수 없습니다.");
            }
        } else {
            model.addAttribute("message", "로그인이 필요합니다.");
        }

        return "profile/main"; // profilePage.html로 이동
    }
}
