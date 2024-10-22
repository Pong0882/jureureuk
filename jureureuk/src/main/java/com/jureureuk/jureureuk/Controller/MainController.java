package com.jureureuk.jureureuk.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.service.CocktailService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private CocktailService cocktailService;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String beforeLogin(Model model) {
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
    public String randomMore(Model model) {
        List<Cocktail> randomMore = cocktailService.getRecentType2Cocktails();

        model.addAttribute("cocktails", randomMore);
        // 소개 페이지로 이동
        return "more/randomMore";
    }

    @GetMapping("/more/popularMore")
    public String popularMore(Model model) {
        List<Cocktail> poppularMore = cocktailService.findAllPopularCocktailsByLikes();

        model.addAttribute("cocktails", poppularMore);
        // 소개 페이지로 이동
        return "more/popularMore";
    }

    // 상위 10개 칵테일을 반환하는 API
    @GetMapping("/api/cocktails/top10")
    @ResponseBody // JSON으로 응답
    public List<Cocktail> getTop10Cocktails() {
        return cocktailService.getTop10Cocktails();
    }
}
