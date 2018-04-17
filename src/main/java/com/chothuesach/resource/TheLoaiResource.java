package com.chothuesach.resource;

import com.chothuesach.jsonview.TheLoaiView;
import com.chothuesach.model.TheLoai;
import com.chothuesach.service.TheLoaiService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/theloai")
public class TheLoaiResource {

    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    @JsonView(TheLoaiView.Overview.class)
    public List<TheLoai> getAllTheLoai(@RequestParam(required = false, defaultValue = "") String tenTheLoai, @PageableDefault(page = 0, size = 20, sort = {"maTheLoai"}, direction = Sort.Direction.ASC)Pageable pageable) {
        return theLoaiService.getDanhSachTheLoai(tenTheLoai, pageable);
    }

    @GetMapping("/{slug}")
    @JsonView(TheLoaiView.Detailed.class)
    public TheLoai getTheLoaiBySlug(@PathVariable String slug) {
        return theLoaiService.getTheLoaiBySlug(slug);
    }

    @PostMapping
    public ResponseEntity addNewTheLoai(@RequestParam String tenTheLoai) {
        theLoaiService.addNewTheLoai(tenTheLoai);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{slug}")
    public TheLoai updateTenTheLoai(@PathVariable String slug, @RequestParam String newTenTheLoai) {
        return theLoaiService.changeTenTheLoai(slug, newTenTheLoai);
    }

    @DeleteMapping("/{slug}")
    public void deleteTheLoai(@PathVariable String slug) {
        theLoaiService.deleteTheLoai(slug);
    }

}
