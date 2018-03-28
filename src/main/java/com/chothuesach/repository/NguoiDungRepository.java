package com.chothuesach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chothuesach.model.NguoiDung;

@Repository
public interface NguoiDungRepository extends CrudRepository<NguoiDung, String> {

	@Query(value = "SELECT PASSWORD FROM NGUOI_DUNG WHERE MA_NGUOI_DUNG = ?1", nativeQuery = true)
	public String getPasswordByUsername(String tenNguoiDung);
	
	NguoiDung findOneByTenNguoiDung(String tenNguoiDung);
	
	NguoiDung findOneByEmail(String email);

	NguoiDung findOneBySoCmnd(String soCmnd);

}
