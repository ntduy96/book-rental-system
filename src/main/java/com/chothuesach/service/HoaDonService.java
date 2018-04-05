package com.chothuesach.service;

import com.chothuesach.dto.ChiTietHoaDonDto;
import com.chothuesach.dto.HoaDonDto;
import com.chothuesach.exception.BookNotFoundException;
import com.chothuesach.exception.HoaDonNotFoundException;
import com.chothuesach.exception.UserNotFoundException;
import com.chothuesach.model.ChiTietHoaDon;
import com.chothuesach.model.ChiTietHoaDonId;
import com.chothuesach.model.HoaDon;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
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
    private SachRepository sachRepository;

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

    public void thanhToanHoaDon(String maHoaDon, String maNhanVien) throws Exception {
        Optional<HoaDon> optional = hoaDonRepository.findById(maHoaDon);
        if (optional.isPresent()) {
            throw new Exception();
        } else {
            HoaDon hoaDon = optional.get();
            hoaDon.setNhanVien(nhanVienRepository.findById(maNhanVien).get());
            hoaDon.setNgayThanhToan(new Date());
            hoaDonRepository.save(hoaDon);
        }
    }

    public HoaDon taoHoaDon(HoaDonDto hoaDonDto) throws Exception {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setKhachHang(nguoiDungRepository.findById(hoaDonDto.getMaKhachHang()).orElseThrow(UserNotFoundException::new));
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        double giaTri = 0;
        Iterator<ChiTietHoaDonDto> iterator = hoaDonDto.getChiTietHoaDonList().listIterator();
        while (iterator.hasNext()) {
            ChiTietHoaDonDto chiTietHoaDonDto = iterator.next();
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setChiTietHoaDonId(new ChiTietHoaDonId(chiTietHoaDonDto.getMaSach(), savedHoaDon.getMaHoaDon()));
            chiTietHoaDon.setHoaDon(savedHoaDon);
            chiTietHoaDon.setSach(sachRepository.findById(chiTietHoaDonDto.getMaSach()).orElseThrow(BookNotFoundException::new));
            chiTietHoaDon.setDonGiaBan(chiTietHoaDonDto.getDonGiaBan());
            chiTietHoaDon.setSoLuongBan(chiTietHoaDonDto.getSoLuongBan());
            ChiTietHoaDon saved = chiTietHoaDonRepository.save(chiTietHoaDon);
            giaTri += saved.getDonGiaBan() * saved.getSoLuongBan();
        }
        savedHoaDon.setGiaTri(giaTri);
        return hoaDonRepository.save(savedHoaDon);
    }

    public void xoaHoaDon(String maHoaDon) {
        hoaDonRepository.deleteHoaDonByMaHoaDon(maHoaDon);
    }

}
