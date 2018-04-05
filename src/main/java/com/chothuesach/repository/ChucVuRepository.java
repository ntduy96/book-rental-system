package com.chothuesach.repository;

import com.chothuesach.model.ChucVu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChucVuRepository extends CrudRepository<ChucVu, Short> {

    List<ChucVu> getChucVuByOrderByMaChucVu();

    ChucVu getChucVuByTenChucVu(String tenChucVu);

}
