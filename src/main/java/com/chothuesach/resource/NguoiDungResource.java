package com.chothuesach.resource;

import com.chothuesach.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungResource {

	@Autowired
	private NguoiDungService nguoiDungService;

	@GetMapping("/check")
	public HashMap<String, Boolean> existenceCheck(
			@RequestParam(name = "ten-nguoi-dung", required = false) String tenNguoiDung,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "so-cmnd", required = false) String soCmnd) {

		if (tenNguoiDung != null) return tenNguoiDungExistenceCheck(tenNguoiDung);
		
		if (email != null) return emailExistenceCheck(email);
		
		if (soCmnd != null) return soCmndExistenceCheck(soCmnd);
		
		return null;

	}

	private HashMap<String, Boolean> tenNguoiDungExistenceCheck(
			@RequestParam(name = "ten-nguoi-dung") String tenNguoiDung) {
		Boolean isExisted = nguoiDungService.tenNguoiDungExist(tenNguoiDung);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("isExisted", isExisted);
		return response;
	}

	private HashMap<String, Boolean> emailExistenceCheck(@RequestParam(name = "email") String email) {
		Boolean isExisted = nguoiDungService.emailExist(email);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("isExisted", isExisted);
		return response;
	}

	private HashMap<String, Boolean> soCmndExistenceCheck(@RequestParam(name = "so-cmnd") String soCmnd) {
		Boolean isExisted = nguoiDungService.soCmndExist(soCmnd);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("isExisted", isExisted);
		return response;
	}

}
