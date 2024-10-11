package com.jureureuk.jureureuk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jureureuk.jureureuk.Entity.Cocktail;
import com.jureureuk.jureureuk.Repository.CocktailRepository;

@Controller
public class CocktailController {

    @Autowired
    private CocktailRepository cocktailRepository;

    @GetMapping("/cocktails")
    public String getCocktails(Model model) {
        List<Cocktail> cocktails = cocktailRepository.findAll();
        model.addAttribute("cocktails", cocktails);
        return "sharing/main";
    }
}
