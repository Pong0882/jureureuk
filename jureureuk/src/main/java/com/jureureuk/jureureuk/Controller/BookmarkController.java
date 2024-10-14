package com.jureureuk.jureureuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

    @GetMapping("")
    public String sharing() {
        return "bookmark/main";
    }
}
