package com.chothuesach.service;

import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.ChucVu;
import com.chothuesach.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    public ChucVu getByMaChucVu(short maChucVu) {
        Optional<ChucVu> result = chucVuRepository.findById(maChucVu);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't find any Chuc vu matching maChucVu: " + maChucVu);
    }

    public ChucVu getByTenChucVu(String tenChucVu) {
        Optional<ChucVu> result = chucVuRepository.getChucVuByTenChucVu(tenChucVu);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Can't find any Chuc vu matching tenChucVu: " + tenChucVu);
    }

    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.getChucVuByOrderByMaChucVu();
    }

}
