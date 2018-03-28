package com.chothuesach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chothuesach.model.DonGiaBan;
import com.chothuesach.model.DonGiaBanId;

public interface DonGiaBanRepository extends CrudRepository<DonGiaBan, DonGiaBanId> {

	@Query(value = "SELECT * FROM DON_GIA_BAN WHERE MA_SACH = ?1 ORDER BY THOI_GIAN DESC LIMIT 1", nativeQuery = true)
	public DonGiaBan getLatestDonGiaBanOfSach(String sachId);

}
