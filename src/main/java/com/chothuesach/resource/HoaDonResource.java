package com.chothuesach.resource;

import com.chothuesach.dto.HoaDonDto;
import com.chothuesach.jsonview.HoaDonView;
import com.chothuesach.model.HoaDon;
import com.chothuesach.service.HoaDonService;
import com.chothuesach.service.NguoiDungService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonResource {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping
    @JsonView(HoaDonView.Overview.class)
    public Iterable<HoaDon> getAllHoaDon() {
        return hoaDonService.getAllHoaDon();
    }

    @GetMapping("/cuatoi")
    @JsonView(HoaDonView.Overview.class)
    public Iterable<HoaDon> getMyHoaDon(Principal principal) {
        return hoaDonService.getHoaDonOfKhachHang(nguoiDungService.getOneByUsername(principal.getName()));
    }

    @GetMapping("/{maHoaDon}")
    @JsonView(HoaDonView.Detailed.class)
    public HoaDon getHoaDonByMaHoaDon(@PathVariable String maHoaDon) {
        return hoaDonService.getHoaDonByMaHoaDon(maHoaDon);
    }

    @PostMapping(consumes = "application/json")
    @JsonView(HoaDonView.Detailed.class)
    public ResponseEntity<Object> createNewHoaDon(Principal principal, @Valid @RequestBody HoaDonDto hoaDonDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            HoaDon hoaDon = hoaDonService.taoHoaDon(principal.getName(), hoaDonDto);
            HashMap<String, Object> response = new HashMap<>();
            response.put("created", "ok");
            response.put("hoaDon", hoaDon);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{maHoaDon}")
    @JsonView(HoaDonView.Detailed.class)
    @RolesAllowed(value = { "ROLE_ADMIN", "ROLE_STAFF" })
    public HoaDon thanhToanHoaDon(@PathVariable String maHoaDon, Principal principal) {
        return hoaDonService.thanhToanHoaDon(maHoaDon, principal.getName());
    }

    @DeleteMapping("/{maHoaDon}")
    @RolesAllowed(value = { "ROLE_ADMIN", "ROLE_STAFF" })
    public ResponseEntity xoaHoaDon(@PathVariable String maHoaDon) {
        hoaDonService.xoaHoaDon(maHoaDon);
        return new ResponseEntity(HttpStatus.OK);
    }

}
