package com.jureureuk.jureureuk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.service.CocktailService;

@Controller
@RequestMapping("/cocktail")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping("/{id}")
    public String showCocktailById(@PathVariable("id") Long id, Model model) {
        Cocktail cocktail = cocktailService.getCocktailById(id);

        if (cocktail != null) {
            model.addAttribute("cocktail", cocktail);

            System.out.println(cocktail.getName());
            System.out.println(cocktail.getDescription());
        } else {
            model.addAttribute("message", "칵테일 정보를 찾을 수 없습니다.");
        }
        return "cocktail/cocktailInfo"; // mojitoDetail.html로 포워드
    }

    // 좋아요 수 증가 처리
    @PostMapping("/{id}/like")
    public String updateLike(@PathVariable("id") Long id) {
        cocktailService.incrementLikeCount(id);
        return "redirect:/cocktail/" + id; // 성공 시 상세 페이지로 리다이렉트
    }

    // 즐겨찾기 수 증가 처리
    @PostMapping("/{id}/bookmark")
    public String updateBookmark(@PathVariable("id") Long id) {
        cocktailService.incrementBookmarkCount(id);
        return "redirect:/cocktail/" + id; // 성공 시 상세 페이지로 리다이렉트
    }

    @GetMapping("/top")
    public String showTop10Cocktails(Model model) {
        List<Cocktail> top10Cocktails = cocktailService.getTop10CocktailsByLikes();
        model.addAttribute("cocktails", top10Cocktails);
        return "topCocktailsPageTest"; // 해당 HTML 파일로 이동
    }

    // 모든 칵테일을 가져와서 뷰에 전달
    @GetMapping("/list")
    public String listCocktails(Model model) {
        List<Cocktail> cocktails = cocktailService.findAllCocktails();
        model.addAttribute("cocktails", cocktails);
        return "cocktail/list"; // `templates/cocktail/list.html`로 연결
    }

    // 새로운 칵테일 등록 페이지
    @GetMapping("/new")
    public String newCocktailForm(Model model) {
        model.addAttribute("cocktail", new Cocktail());
        return "cocktail/new"; // `templates/cocktail/new.html`로 연결
    }

    // 새로운 칵테일 저장
    @PostMapping("/save")
    public String saveCocktail(@ModelAttribute Cocktail cocktail) {
        cocktailService.saveCocktail(cocktail);
        return "redirect:/cocktail/list"; // 저장 후 리스트 페이지로 리다이렉트
    }

    // // 특정 칵테일 조회
    // @GetMapping("/{id}")
    // public String viewCocktail(@PathVariable int id, Model model) {
    // Cocktail cocktail = cocktailService.getCocktailById(id);
    // model.addAttribute("cocktail", cocktail);
    // return "cocktail/view"; // `templates/cocktail/view.html`로 연결
    // }

    // 칵테일 삭제
    @GetMapping("/delete/{id}")
    public String deleteCocktail(@PathVariable Long id) {
        cocktailService.deleteCocktail(id);
        return "redirect:/cocktail/list"; // 삭제 후 리스트 페이지로 리다이렉트
    }
}