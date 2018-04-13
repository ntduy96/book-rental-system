package com.chothuesach.repository;

import com.chothuesach.model.NguoiDung;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends CrudRepository<NguoiDung, String> {

	@Query(value = "SELECT PASSWORD FROM NGUOI_DUNG WHERE MA_NGUOI_DUNG = ?1", nativeQuery = true)
	public String getPasswordByUsername(String tenNguoiDung);
	
	Optional<NguoiDung> findOneByTenNguoiDung(String tenNguoiDung);

	Optional<NguoiDung> findOneByEmail(String email);

	Optional<NguoiDung> findOneBySoCmnd(String soCmnd);

}
