package com.chothuesach.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chothuesach.model.TacGia;

@Repository
public interface TacGiaRepository extends CrudRepository<TacGia, String> {

}
