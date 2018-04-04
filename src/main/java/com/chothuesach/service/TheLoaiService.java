package com.chothuesach.service;

import com.chothuesach.model.TheLoai;
import com.chothuesach.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheLoaiService {

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    public List<TheLoai> getDanhSachTheLoai() {
        return theLoaiRepository.findAll();
    }

    public TheLoai getTheLoaiBySlug(String slug) {
        return theLoaiRepository.getBySlug(slug);
    }

}
