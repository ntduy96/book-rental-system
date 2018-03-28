package com.chothuesach.controller;

import java.security.Principal;
import java.util.HashMap;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/user")
	@RolesAllowed(value = { "ROLE_ADMIN", "ROLE_USER", "ROLE_STAFF" })
	public HashMap<String, Object> getUser() {
//		String user = principal.getName();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("user", principal);
		return res;
	}
	
//	@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
	@GetMapping("/authority")
	public HashMap<String, Object> getUserAuthority(Authentication authentication) {
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Object authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("authorities", authorities);
		return res;
	}
	
	@GetMapping("/authority/admin")
	public HashMap<String, Object> isAdmin(HttpServletRequest request) {
		Boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("isAdmin", isAdmin);
		return res;
	}
	
	@GetMapping("/authority/staff")
	public HashMap<String, Object> isStaff(HttpServletRequest request) {
		Boolean isStaff = request.isUserInRole("ROLE_STAFF");
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("isAdmin", isStaff);
		return res;
	}
	
	@GetMapping("/authority/user")
	public HashMap<String, Object> isUser(HttpServletRequest request) {
		Boolean isUser = request.isUserInRole("ROLE_USER");
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("isAdmin", isUser);
		return res;
	}

}
