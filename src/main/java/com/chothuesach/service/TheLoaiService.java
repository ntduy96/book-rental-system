package com.chothuesach.service;

import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.TheLoai;
import com.chothuesach.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheLoaiService {

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    public List<TheLoai> getDanhSachTheLoai(String tenTheLoai, Pageable pageable) {
        return theLoaiRepository.getByTenTheLoaiContains(tenTheLoai, pageable);
    }

    public TheLoai getTheLoaiBySlug(String slug) {
        Optional<TheLoai> result = theLoaiRepository.getBySlug(slug);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't found any TheLoai matching " + slug);
    }

    public TheLoai getTheLoaiByTenTheLoai(String tenTheLoai) {
        Optional<TheLoai> result = theLoaiRepository.getByTenTheLoai(tenTheLoai);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't found any TheLoai matching " + tenTheLoai);
    }

    public void addNewTheLoai(String tenTheLoai) {
        TheLoai newTheLoai = new TheLoai();
        newTheLoai.setTenTheLoai(tenTheLoai);
        theLoaiRepository.save(newTheLoai);
    }

    public void changeTenTheLoai(String slug, String newTenTheLoai) {
        TheLoai theLoai = getTheLoaiBySlug(slug);
        theLoai.setTenTheLoai(newTenTheLoai);
        theLoaiRepository.save(theLoai);
    }

    public void deleteTheLoai(String slug) {
        TheLoai theLoai = getTheLoaiBySlug(slug);
        theLoaiRepository.delete(theLoai);
    }

}
