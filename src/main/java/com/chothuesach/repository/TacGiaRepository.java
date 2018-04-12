package com.chothuesach.repository;

import com.chothuesach.model.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TacGiaRepository extends JpaRepository<TacGia, String> {

    Optional<TacGia> getBySlug(String slug);

    Optional<TacGia> getByTenTacGia(String tenTacGia);

}
