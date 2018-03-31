package com.chothuesach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage(ModelMap model, Principal principal) {
        if (principal != null) {
            model.addAttribute("user", principal.getName());
        }
        return "index";
    }

}
