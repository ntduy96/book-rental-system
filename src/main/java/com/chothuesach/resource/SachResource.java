package com.chothuesach.resource;

import com.chothuesach.dto.SachDto;
import com.chothuesach.dto.SachUpdateDto;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.model.DonGiaBan;
import com.chothuesach.model.Sach;
import com.chothuesach.repository.DonGiaBanRepository;
import com.chothuesach.service.SachService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/sach")
public class SachResource {
	
	@Autowired
	private SachService sachService;

	@Autowired
	private DonGiaBanRepository donGiaBanRepository;
	
	@GetMapping
	@JsonView(SachView.Overview.class)
	public List<Sach> getAll(@RequestParam(required = false) String tenSach, @PageableDefault(page = 0, size = 10, sort = {"ngayTao"}, direction = Sort.Direction.DESC) Pageable pageable) {
		if (tenSach != null) {
		    return sachService.searchByTenSach(tenSach, pageable);
        }
	    return sachService.searchByTenSach("", pageable);
	}
	
	@GetMapping("/tenSach")
	@JsonView(SachView.Overview.class)
	public Sach getOneByTenSach(@RequestParam String tenSach, @PageableDefault(page = 0, size = 3) Pageable pageable) {
		return sachService.getOneByTenSach(tenSach);
	}
	
	@GetMapping("/{slug}")
	@JsonView(SachView.Detailed.class)
	public Sach getOneBySlug(@PathVariable String slug) {
		return sachService.getOneBySlug(slug);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(consumes = "application/json")
	@JsonView(SachView.Detailed.class)
	public ResponseEntity addNewBook(@RequestBody @Valid SachDto sachDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            try {
                Sach sach = sachService.createNewSach(sachDto);
                HashMap<String, Object> response = new HashMap<>();
                response.put("created", "ok");
                response.put("sach", sach);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{slug}", consumes = "application/json")
	@JsonView(SachView.Detailed.class)
	public ResponseEntity updateSach(@PathVariable String slug, @RequestBody @Valid SachUpdateDto sachUpdateDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            HashMap<String, Object> response = new HashMap<>();
            Sach sach = sachService.updateSach(slug, sachUpdateDto);
            if (sach != null) {
                response.put("updated", "ok");
                response.put("sach", sach);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("updated", "failed");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{slug}")
	public HashMap<String, String> deleteSach(@PathVariable String slug) {
		sachService.deleteSachBySlug(slug);
		HashMap<String, String> response = new HashMap<>();
		response.put("deleted", "ok");
		return response;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{slug}/anhBia")
	public void changeAnhBia(@PathVariable String slug, @RequestParam("anhBia") MultipartFile file) {
		sachService.setAnhBia(slug, file);
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{slug}/prices")
	public ResponseEntity updatePrice(@PathVariable String slug, @RequestParam double newPrice) {
		Sach sach = sachService.getOneBySlug(slug);
		HashMap<String, String> response = new HashMap<>();
		if (sach == null) {
			response.put("error", "sach not found!");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		}
		DonGiaBan newDonGiaBan = new DonGiaBan();
		newDonGiaBan.setDonGia(newPrice);
		newDonGiaBan.setSach(sach);
		donGiaBanRepository.save(newDonGiaBan);
		response.put("updated", "ok");
		return new ResponseEntity(response, HttpStatus.OK);
	}

}
