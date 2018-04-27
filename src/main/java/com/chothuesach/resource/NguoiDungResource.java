package com.chothuesach.resource;

import com.chothuesach.jsonview.NguoiDungView;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.service.NguoiDungService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungResource {

	@Autowired
	private NguoiDungService nguoiDungService;

	@GetMapping("/me")
	@PreAuthorize("hasRole('ROLE_USER')")
	@JsonView(NguoiDungView.Detailed.class)
	public NguoiDung getMyProfile(Principal principal) {
		return nguoiDungService.getOneByUsername(principal.getName());
	}

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

	@PostMapping("/anhDaiDien")
	public void changeAnhDaiDien(@RequestParam("anhDaiDien") MultipartFile file, Principal principal) {
		nguoiDungService.setAnhDaiDien(principal.getName(), file);
	}

	@PostMapping("/{tenNguoiDung}/anhDaiDien")
	public void changeAnhDaiDien(@PathVariable String tenNguoiDung, @RequestParam("anhDaiDien") MultipartFile file) {
		nguoiDungService.setAnhDaiDien(tenNguoiDung, file);
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
