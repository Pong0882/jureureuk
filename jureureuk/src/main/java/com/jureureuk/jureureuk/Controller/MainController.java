package com.jureureuk.jureureuk.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.service.CocktailService;

@Controller
public class MainController {

    @Autowired
    private CocktailService cocktailService;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String beforeLogin(Model model) {
        List<Cocktail> top10Cocktails = cocktailService.getTop10CocktailsByLikes();

        // 로그를 찍어 리스트가 제대로 불러와졌는지 확인
        if (top10Cocktails != null && !top10Cocktails.isEmpty()) {
            logger.info("Top 10 Cocktails:");
            for (Cocktail cocktail : top10Cocktails) {
                logger.info("Cocktail Name: {}, Likes: {}", cocktail.getName(), cocktail.getLikesCount());

            }
        } else {
            logger.warn("No cocktails found in the list.");

        }

        model.addAttribute("cocktails", top10Cocktails);
        return "beforeLogin"; // 해당 HTML 파일로 이동
    }

    @GetMapping("/main")
    public String showTop10Cocktails(Model model) {
        List<Cocktail> top10Cocktails = cocktailService.getTop10CocktailsByLikes(); // 인기칵테일
        List<Cocktail> top10Cocktails2 = cocktailService.getTop10RandomCocktailsByLikes(); // 랜덤 칵테일

        // 로그를 찍어 리스트가 제대로 불러와졌는지 확인
        if (top10Cocktails != null && !top10Cocktails.isEmpty()) {
            logger.info("Top 10 Cocktails:");
            for (Cocktail cocktail : top10Cocktails) {
                logger.info("Cocktail Name: {}, Likes: {}", cocktail.getName(), cocktail.getLikesCount());

            }
        } else {
            logger.warn("No cocktails found in the list.");

        } // 인기 칵테일

        // 로그를 찍어 리스트가 제대로 불러와졌는지 확인
        if (top10Cocktails2 != null && !top10Cocktails2.isEmpty()) {
            logger.info("Top 10 Cocktails:");
            for (Cocktail cocktail : top10Cocktails2) {
                logger.info("Cocktail Name: {}, Likes: {}", cocktail.getName(), cocktail.getLikesCount());

            }
        } else {
            logger.warn("No cocktails found in the list.");

        } // 랜덤 칵테일

        model.addAttribute("cocktails", top10Cocktails);
        model.addAttribute("cocktails2", top10Cocktails2);
        return "mainPage"; // 해당 HTML 파일로 이동
    }

    @GetMapping("/more/randomMore")
    public String randomMore() {
        // 소개 페이지로 이동
        return "more/randomMore";
    }

    @GetMapping("/more/popularMore")
    public String popularMore() {
        // 소개 페이지로 이동
        return "more/popularMore";
    }

    // @GetMapping("/contact")
    // public String contactPage() {
    // // 연락처 페이지로 이동
    // return "contact";
    // }

    // @GetMapping("/login")
    // public String loginPage() {
    // // 로그인 페이지로 이동
    // return "login";
    // }

    // @GetMapping("/profile")
    // public String profilePage(Model model) {
    // // 프로필 페이지로 이동
    // // 모델에 사용자 관련 정보를 추가할 수 있음
    // return "profile";
    // }

    // @GetMapping("/cocktail")
    // public String cocktailPage() {
    // // 칵테일 리스트 페이지로 이동
    // return "cocktail";
    // }

    // @GetMapping("/help")
    // public String helpPage() {
    // // 도움말 페이지로 이동
    // return "help";
    // }
}
