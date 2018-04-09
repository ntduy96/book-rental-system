package com.chothuesach.repository;

import com.chothuesach.model.TacGia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacGiaRepository extends CrudRepository<TacGia, String> {

    List<TacGia> findAll();

    TacGia getBySlug(String slug);

    TacGia getByTenTacGia(String tenTacGia);

}
