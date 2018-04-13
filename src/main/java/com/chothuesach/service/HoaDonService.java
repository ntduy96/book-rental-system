package com.chothuesach.service;

import com.chothuesach.dto.ChiTietHoaDonDto;
import com.chothuesach.dto.HoaDonDto;
import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.*;
import com.chothuesach.repository.ChiTietHoaDonRepository;
import com.chothuesach.repository.HoaDonRepository;
import com.chothuesach.repository.NguoiDungRepository;
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
    private NhanVienService nhanVienService;

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
        Optional<HoaDon> optional = hoaDonRepository.findById(maHoaDon);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new ResourceNotFoundException("Can't find any Hoa don matching maHoaDon: " + maHoaDon);
    }

    public List<HoaDon> getHoaDonOfKhachHang(NguoiDung khachHang) {
        return hoaDonRepository.getByKhachHangOrderByNgayLapHoaDonDesc(khachHang);
    }

    public HoaDon thanhToanHoaDon(String maHoaDon, String tenNhanVien) {
        HoaDon hoaDon = getHoaDonByMaHoaDon(maHoaDon);
        NhanVien nhanVien = nhanVienService.getByTenNguoiDung(tenNhanVien);
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setHoTenNhanVien(nhanVien.getHoTenNguoiDung());
        hoaDon.setNgayThanhToan(new Date());
        return hoaDonRepository.save(hoaDon);
    }

    public HoaDon taoHoaDon(String tenNguoiDung, HoaDonDto hoaDonDto) {
        HoaDon hoaDon = new HoaDon();
        NguoiDung khachHang = nguoiDungRepository.findOneByTenNguoiDung(tenNguoiDung);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setHoTenKhachHang(khachHang.getHoTenNguoiDung());
        hoaDon.setSoCmndKhachHang(khachHang.getSoCmnd());
        hoaDon.setDiaChiKhachHang(khachHang.getDiaChiNguoiDung());
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        double giaTri = 0;
        for (ChiTietHoaDonDto chiTietHoaDonDto : hoaDonDto.getChiTietHoaDonList()) {
            Sach sach = sachService.getOneByMaSach(chiTietHoaDonDto.getMaSach());
            if (sach.getSoLuong() < chiTietHoaDonDto.getSoLuongBan()) continue;
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setChiTietHoaDonId(new ChiTietHoaDonId(chiTietHoaDonDto.getMaSach(), savedHoaDon.getMaHoaDon()));
            chiTietHoaDon.setHoaDon(savedHoaDon);
            chiTietHoaDon.setSach(sach);
            chiTietHoaDon.setTenSach(sach.getTenSach());
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
