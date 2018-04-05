package com.chothuesach.resource;

import com.chothuesach.dto.NhanVienDto;
import com.chothuesach.jsonview.NhanVienView;
import com.chothuesach.model.NhanVien;
import com.chothuesach.service.NhanVienService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienResource {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    @JsonView(NhanVienView.Overview.class)
    public List<NhanVien> getDanhSachNhanVien(@RequestParam(required = false) Short maChucVu) {
        if (maChucVu != null) {
            return nhanVienService.getAllNhanVienByChucVu(maChucVu);
        }
        return nhanVienService.getAllNhanVien();
    }

    @GetMapping("/{tenNguoiDung}")
    @JsonView(NhanVienView.Detailed.class)
    public NhanVien getThongTinNhanVien(@PathVariable String tenNguoiDung) {
        return nhanVienService.getByTenNguoiDung(tenNguoiDung);
    }

    @PostMapping
    @JsonView(NhanVienView.Detailed.class)
    public ResponseEntity registerNewNhanVien(@Valid NhanVienDto nhanVienDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            try {
                NhanVien nhanVien = nhanVienService.createNewNhanVien(nhanVienDto);
                HashMap<String, Object> response = new HashMap<>();
                response.put("created", "ok");
                response.put("nhanVien", nhanVien);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
    }

    @DeleteMapping("/{maNhanVien}")
    public ResponseEntity deleteNhanVien(@PathVariable String maNhanVien) {
        nhanVienService.deleteNhanVien(maNhanVien);
        HashMap<String, Object> response = new HashMap<>();
        response.put("deleted", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
