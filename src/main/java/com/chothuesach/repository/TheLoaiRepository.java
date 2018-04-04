package com.chothuesach.repository;

import com.chothuesach.model.TheLoai;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TheLoaiRepository extends CrudRepository<TheLoai, Long> {

    List<TheLoai> findAll();

    TheLoai getBySlug(String slug);

}
