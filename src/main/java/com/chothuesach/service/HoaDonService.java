package com.chothuesach.service;

import com.chothuesach.dto.ChiTietHoaDonDto;
import com.chothuesach.dto.HoaDonDto;
import com.chothuesach.exception.HoaDonNotFoundException;
import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.*;
import com.chothuesach.repository.ChiTietHoaDonRepository;
import com.chothuesach.repository.HoaDonRepository;
import com.chothuesach.repository.NguoiDungRepository;
import com.chothuesach.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private SachService sachService;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public Iterable<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    public HoaDon getHoaDonByMaHoaDon(String maHoaDon) {
        return hoaDonRepository.findById(maHoaDon).orElseThrow(HoaDonNotFoundException::new);
    }

    public List<HoaDon> getHoaDonOfKhachHang(NguoiDung khachHang) {
        return hoaDonRepository.getByKhachHangOrderByNgayLapHoaDonDesc(khachHang);
    }

    public void thanhToanHoaDon(String maHoaDon, String maNhanVien) {
        Optional<HoaDon> optional = hoaDonRepository.findById(maHoaDon);
        if (optional.isPresent()) {
            throw new ResourceNotFoundException("Can't find any Hoa don matching maHoaDon: " + maHoaDon);
        } else {
            HoaDon hoaDon = optional.get();
            hoaDon.setNhanVien(nhanVienRepository.findById(maNhanVien).get());
            hoaDon.setNgayThanhToan(new Date());
            hoaDonRepository.save(hoaDon);
        }
    }

    public HoaDon taoHoaDon(String tenNguoiDung, HoaDonDto hoaDonDto) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setKhachHang(nguoiDungRepository.findOneByTenNguoiDung(tenNguoiDung));
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        double giaTri = 0;
        for (ChiTietHoaDonDto chiTietHoaDonDto : hoaDonDto.getChiTietHoaDonList()) {
            Sach sach = sachService.getOneByMaSach(chiTietHoaDonDto.getMaSach());
            if (sach.getSoLuong() < chiTietHoaDonDto.getSoLuongBan()) continue;
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setChiTietHoaDonId(new ChiTietHoaDonId(chiTietHoaDonDto.getMaSach(), savedHoaDon.getMaHoaDon()));
            chiTietHoaDon.setHoaDon(savedHoaDon);
            chiTietHoaDon.setSach(sach);
            chiTietHoaDon.setDonGiaBan(sachService.getLatestPrice(sach.getSlug()).donGia);
            chiTietHoaDon.setSoLuongBan(chiTietHoaDonDto.getSoLuongBan());
            ChiTietHoaDon saved = chiTietHoaDonRepository.save(chiTietHoaDon);
            sachService.changeSoLuongSach(sach.getSlug(), sach.getSoLuong() - chiTietHoaDonDto.getSoLuongBan());
            giaTri += saved.getDonGiaBan() * saved.getSoLuongBan();
        }
        savedHoaDon.setGiaTri(giaTri);
        return hoaDonRepository.save(savedHoaDon);
    }

    public void xoaHoaDon(String maHoaDon) {
        hoaDonRepository.deleteHoaDonByMaHoaDon(maHoaDon);
    }

}
