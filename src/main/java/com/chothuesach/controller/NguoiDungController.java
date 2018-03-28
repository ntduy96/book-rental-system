package com.chothuesach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chothuesach.model.NguoiDung;
import com.chothuesach.repository.NguoiDungRepository;

@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@GetMapping("/{username}")
	public NguoiDung getByUsername(@PathVariable String username) {
		return nguoiDungRepository.findOneByTenNguoiDung(username);
	}
	
}
