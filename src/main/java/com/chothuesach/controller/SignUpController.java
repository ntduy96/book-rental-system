package com.chothuesach.controller;

import com.chothuesach.dto.NguoiDungDto;
import com.chothuesach.exception.EmailExistsException;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid NguoiDungDto nguoiDungDto,
											BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("redirect:signup");
		}
		try {
			NguoiDung newNguoiDung = nguoiDungService.registerNewNguoiDung(nguoiDungDto);
			if (newNguoiDung != null) {
				model.addAttribute("success", true);
				return new ModelAndView("redirect:login", model);
			}
		} catch (EmailExistsException e) {
			model.addAttribute("exist", true);
			model.addAttribute("message", "Email " + nguoiDungDto.getEmail() + " already existed!");
			return new ModelAndView("redirect:login", model);
		}

		return new ModelAndView("signup");
	}

}
