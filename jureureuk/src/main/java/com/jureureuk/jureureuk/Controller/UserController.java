package com.jureureuk.jureureuk.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/loginSuccess")
    public String loginSuccessPage() {
        return "loginSuccessPage"; // templates 폴더의 loginSuccessPage.html을 반환
    }

    @GetMapping("/nickname")
    public String nicknamePage(OAuth2AuthenticationToken authentication, Model model) {
        if (authentication != null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");

            model.addAttribute("googleId", email != null ? email : "Unknown");
            model.addAttribute("name", name != null ? name : "Unknown");
        }
        return "nicknamePage"; // nicknamePage.html로 이동
    }
}
