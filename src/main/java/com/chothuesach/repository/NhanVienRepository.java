package com.chothuesach.repository;

import com.chothuesach.model.ChucVu;
import com.chothuesach.model.NhanVien;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, String> {

    List<NhanVien> getAllByOrderByHoTenNguoiDung();

    List<NhanVien> getAllByChucVu(ChucVu chucVu);

    NhanVien getByTenNguoiDung(String tenNguoiDung);

    @Modifying
    @Query("DELETE FROM NhanVien WHERE MA_NHAN_VIEN = ?1")
    @Transactional
    void deleteNhanVienByMaNguoiDung(String maNguoiDung);

}
