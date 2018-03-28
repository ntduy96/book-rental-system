package com.chothuesach.resource;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chothuesach.dto.SachDto;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.model.Sach;
import com.chothuesach.service.SachService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/resource/sach")
public class SachResource {
	
	@Autowired
	private SachService sachService;
	
	@GetMapping
	@JsonView(SachView.Overview.class)
	public Page<Sach> getAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
		return sachService.getAllSach(pageable);
	}
	
	@GetMapping("/search")
	@JsonView(SachView.Overview.class)
	public List<Sach> searchByTenSach(@RequestParam String tenSach, @PageableDefault(page = 0, size = 3) Pageable pageable) {
		return sachService.searchByTenSach(tenSach, pageable);
	}
	
	@GetMapping("/{slug}")
	@JsonView(SachView.Detailed.class)
	public Sach getOneBySlug(@PathVariable String slug) {
		return sachService.getOneBySlug(slug);
	}
	
	@PostMapping
	public HashMap<String, String> addNewBook(@Valid SachDto sachDto) {
		Sach sach = sachService.createNewSach(sachDto);
		HashMap<String, String> response = new HashMap<String, String>();
		if (sach != null) {
			response.put("created", "ok");
			response.put("maSach", sach.getMaSach());
			response.put("url", "/resource/sach/" + sach.getSlug());
			return response;
		} else {
			response.put("created", "failed");
			return response;
		}
	}
	
	@PutMapping("/{slug}")
	public HashMap<String, String> changeSoLuongSach(@PathVariable String slug, @RequestParam int newSoLuong) {
		sachService.changeSoLuongSach(slug, newSoLuong);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("updated", "ok");
		return response;
	}
	
	@DeleteMapping("/{slug}")
	public HashMap<String, String> deleteSach(@PathVariable String slug) {
		sachService.deleteSachBySlug(slug);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("deleted", "ok");
		return response;
	}
	
}
