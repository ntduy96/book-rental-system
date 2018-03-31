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

	    if (tenNguoiDung != null) {
	        return addJsonField(nguoiDungService.tenNguoiDungExist(tenNguoiDung));
        }

        if (email != null) {
            return addJsonField(nguoiDungService.emailExist(email));
        }

        if (soCmnd != null) {
            return addJsonField(nguoiDungService.soCmndExist(soCmnd));
        }

        return null;

    }

    private HashMap<String, Boolean> addJsonField(boolean result) {
	    HashMap<String, Boolean> json = new HashMap<>();
	    json.put("exist", result);
	    return json;
    }
	
}
