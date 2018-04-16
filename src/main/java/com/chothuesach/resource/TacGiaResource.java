package com.chothuesach.resource;

import com.chothuesach.jsonview.TacGiaView;
import com.chothuesach.model.TacGia;
import com.chothuesach.service.TacGiaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tacgia")
public class TacGiaResource {

    @Autowired
    private TacGiaService tacGiaService;

    @GetMapping
    @JsonView(TacGiaView.Overview.class)
    public List<TacGia> getAll() {
        return tacGiaService.getDanhSachTacGia();
    }

    @GetMapping("/{slug}")
    @JsonView(TacGiaView.Detailed.class)
    public TacGia getOneBySlug(@PathVariable String slug) {
        return tacGiaService.getOneBySlug(slug);
    }

    @PostMapping
    public void createNewTacGia(@RequestParam String tenTacGia) {
        tacGiaService.addNewTacGia(tenTacGia);
    }

    @PutMapping("/{slug}")
    @JsonView(TacGiaView.Detailed.class)
    public TacGia updateTenTacGia(@PathVariable String slug, @RequestParam String newTenTacGia) {
        return tacGiaService.changeTenTacGia(slug, newTenTacGia);
    }

    @DeleteMapping("/{slug}")
    public void deleteTacGia(@PathVariable String slug) {
        tacGiaService.deleteTacGia(slug);
    }

}
