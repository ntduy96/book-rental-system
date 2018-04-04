package com.chothuesach.controller;

import com.chothuesach.model.NguoiDung;
import com.chothuesach.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungController {
	
	@Autowired
	private NguoiDungService nguoiDungService;

	@GetMapping("/{username}")
	public NguoiDung getByUsername(@PathVariable String username) {
		return nguoiDungService.getOneByUsername(username);
	}

	@PostMapping("/check")
    public HashMap<String, Boolean> checkExist(@RequestParam(required = false) String tenNguoiDung, @RequestParam(required = false) String email, @RequestParam(required = false) String soCmnd) {
        HashMap<String, Boolean> response = new HashMap<>();

	    if (tenNguoiDung != null) {
	        response.put("tenNguoiDungExist", nguoiDungService.tenNguoiDungExist(tenNguoiDung));
        }

        if (email != null) {
            response.put("emailExist", nguoiDungService.emailExist(email));
        }

        if (soCmnd != null) {
            response.put("soCmndExist", nguoiDungService.soCmndExist(soCmnd));
        }

        return response;

    }
	
}
