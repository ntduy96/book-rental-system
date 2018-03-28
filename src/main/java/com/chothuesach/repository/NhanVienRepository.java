package com.chothuesach.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chothuesach.model.NhanVien;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, String> {

}
