package com.chothuesach.repository;

import com.chothuesach.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {

    Optional<TheLoai> getBySlug(String slug);

    Optional<TheLoai> getByTenTheLoai(String tenTheLoai);

}
