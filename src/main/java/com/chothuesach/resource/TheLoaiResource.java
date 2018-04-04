package com.chothuesach.resource;

import com.chothuesach.jsonview.TheLoaiView;
import com.chothuesach.model.TheLoai;
import com.chothuesach.service.TheLoaiService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/theloai")
public class TheLoaiResource {

    @Autowired
    private TheLoaiService theLoaiService;

    @GetMapping
    @JsonView(TheLoaiView.Overview.class)
    public Iterable<TheLoai> getAllTheLoai() {
        return theLoaiService.getDanhSachTheLoai();
    }

    @GetMapping("/{slug}")
    @JsonView(TheLoaiView.Detailed.class)
    public TheLoai getTheLoaiBySlug(@PathVariable String slug) {
        return theLoaiService.getTheLoaiBySlug(slug);
    }

}
