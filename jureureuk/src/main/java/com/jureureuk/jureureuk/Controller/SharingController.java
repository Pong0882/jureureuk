package com.jureureuk.jureureuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sharing")
public class SharingController {

    @GetMapping("")
    public String sharing() {
        return "sharing/main";
    }

    @GetMapping("/write")
    public String sharingWrite() {
        return "sharing/write";
    }
}
