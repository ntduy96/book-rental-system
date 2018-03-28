package com.chothuesach.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {
	@GetMapping
	public String printHello(HttpSession session, ModelMap model) {
		model.addAttribute("message", session.getAttribute("name"));
		return "hello";
	}
	
	@PostMapping
	public String handleForm(HttpSession session, @RequestParam String name) {
		System.out.println("name" + name);
		session.setAttribute("name", name);
		return "redirect:/hello";
	}
}
