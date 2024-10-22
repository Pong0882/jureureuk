package com.jureureuk.jureureuk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.service.CocktailService;

@Controller
@RequestMapping("/sharing")
public class SharingController {

    private final CocktailService cocktailService;

    @Autowired
    public SharingController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("")
    public String sharing(Model model) {
        List<Cocktail> cocktails = cocktailService.getCocktailsByRecipeType(3);
        model.addAttribute("cocktails", cocktails); // 모델에 추가
        return "sharing/main"; // 템플릿 반환
    }

    @GetMapping("/write")
    public String sharingWrite() {
        return "sharing/write";
    }
}
