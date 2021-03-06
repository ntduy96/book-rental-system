package com.chothuesach.repository;

import com.chothuesach.model.Sach;
import com.chothuesach.model.TheLoai;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, String> {

	Sach findOneByTenSach(String tenSach);

	Sach findOneBySlug(String slug);

	List<Sach> findByTenSachContains(String tenSach, Pageable pageable);

	List<Sach> findBySachThuocTheLoai(TheLoai theLoai);

	@Modifying
	@Query("UPDATE Sach SET TEN_SACH = ?1 WHERE MA_SACH = ?2")
	@Transactional
	void updateTenSachByMaSach(String newTenSach, String maSach);

	@Modifying
	@Query("UPDATE Sach SET SO_LUONG = ?1 WHERE SLUG = ?2")
	@Transactional
	void updateSoLuongSachByMaSach(long newSoLuong, String slug);

	@Modifying
	@Query("DELETE FROM Sach WHERE MA_SACH = ?1")
	@Transactional
	void deleteByMaSach(String maSach);

	@Modifying
	@Query("DELETE FROM Sach WHERE SLUG = ?1")
	@Transactional
	void deleteBySlug(String slug);

}
