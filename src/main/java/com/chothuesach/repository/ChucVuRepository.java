package com.chothuesach.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chothuesach.model.ChucVu;

@Repository
public interface ChucVuRepository extends CrudRepository<ChucVu, Short> {

}
