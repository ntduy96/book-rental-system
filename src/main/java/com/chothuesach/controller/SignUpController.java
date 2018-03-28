package com.chothuesach.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.chothuesach.dto.NguoiDungDto;
import com.chothuesach.service.NguoiDungService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private NguoiDungService nguoiDungService;

	@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		NguoiDungDto nguoiDungDto = new NguoiDungDto();
		model.addAttribute("user", nguoiDungDto);
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
	@ResponseBody
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid NguoiDungDto nguoiDungDto,
			BindingResult result, WebRequest request, Errors errors) {
		if (nguoiDungService.registerNewNguoiDung(nguoiDungDto) != null) {
			return new ModelAndView("login");
		}
		return new ModelAndView("signup");
	}

}
