package com.jureureuk.jureureuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/refrigerator")
public class RefrigeratorController {

    @GetMapping("")
    public String refrigeratorMain() {
        return "refrigerator/main";
    }

    @GetMapping("/materialManagement")
    public String materialManagement() {
        return "refrigerator/materialManagement";
    }

    @GetMapping("/MaterialInfo")
    public String MaterialInfo() {
        return "refrigerator/MaterialInfo";
    }

    @GetMapping("/shopping")
    public String shopping() {
        return "refrigerator/shopping";
    }

}
