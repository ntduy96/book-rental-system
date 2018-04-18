package com.chothuesach.resource;

import com.chothuesach.jsonview.ChucVuView;
import com.chothuesach.model.ChucVu;
import com.chothuesach.service.ChucVuService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chucvu")
public class ChucVuResource {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    @JsonView(ChucVuView.Overview.class)
    public List<ChucVu> getAllChucVu() {
        return chucVuService.getAllChucVu();
    }

    @GetMapping("/{maChucVu}")
    @JsonView(ChucVuView.Detailed.class)
    public ChucVu getChucVuByMaChucVu(@PathVariable Short maChucVu) {
        return chucVuService.getByMaChucVu(maChucVu);
    }

}
