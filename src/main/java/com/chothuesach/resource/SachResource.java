package com.chothuesach.resource;

import com.chothuesach.dto.SachDto;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.model.DonGiaBan;
import com.chothuesach.model.Sach;
import com.chothuesach.service.SachService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/resource/sach")
public class SachResource {
	
	@Autowired
	private SachService sachService;
	
	@GetMapping
	@JsonView(SachView.Overview.class)
	public List<Sach> getAll(@PageableDefault(page = 0, size = 10, sort = {"ngayTao"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return sachService.searchByTenSach("", pageable);
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

	@GetMapping("/theloai/{theLoai}")
	@JsonView(SachView.Overview.class)
	public List<Sach> getSachByTheLoai(@PathVariable String theLoai) {
		return sachService.getSachByTheLoai(theLoai);
	}

	@GetMapping("/{slug}/prices")
	@JsonView(SachView.CurrentPrice.class)
	public List<DonGiaBan> getPrices(@PathVariable String slug) {
		return (List<DonGiaBan>) sachService.getOneBySlug(slug).getDonGiaBan();
	}

	@GetMapping("/{slug}/prices/latest")
	@JsonView(SachView.CurrentPrice.class)
	public DonGiaBan getCurrentPrice(@PathVariable String slug) {
		return sachService.getLatestPrice(slug);
	}

}
