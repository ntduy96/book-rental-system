package com.chothuesach.repository;

import com.chothuesach.model.HoaDon;
import com.chothuesach.model.NguoiDung;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HoaDonRepository extends CrudRepository<HoaDon, String> {

    List<HoaDon> getByKhachHangOrderByNgayLapHoaDonDesc(NguoiDung khachHang);

    @Transactional
    @Modifying
    @Query("DELETE FROM HoaDon WHERE MA_HOA_DON = ?1")
    void deleteHoaDonByMaHoaDon(String maHoaDon);

}
