package com.chothuesach.repository;

import com.chothuesach.model.DonGiaBan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DonGiaBanRepository extends CrudRepository<DonGiaBan, Long> {

	@Query(value = "SELECT * FROM DON_GIA_BAN WHERE MA_SACH = ?1 ORDER BY NGAY_TAO DESC LIMIT 1", nativeQuery = true)
	public DonGiaBan getLatestDonGiaBanOfSach(String sachId);

}
