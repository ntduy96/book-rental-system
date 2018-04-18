package com.chothuesach.service;

import com.chothuesach.exception.ResourceConflictException;
import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.TacGia;
import com.chothuesach.repository.TacGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacGiaService {

    @Autowired
    private TacGiaRepository tacGiaRepository;

    public List<TacGia> getDanhSachTacGia() {
        return tacGiaRepository.findAll(new Sort(Sort.Direction.ASC, "tenTacGia"));
    }

    public TacGia getOneBySlug(String slug) {
        if (tacGiaRepository.getBySlug(slug).isPresent()) {
            return tacGiaRepository.getBySlug(slug).get();
        }
        throw new ResourceNotFoundException("Tac gia not found");
    }

    public TacGia getOneByTenTacGia(String tenTacGia) {
        return tacGiaRepository.getByTenTacGia(tenTacGia).orElseThrow(ResourceNotFoundException::new);
    }

    public void addNewTacGia(String tenTacGia) {
        if (tacGiaRepository.getByTenTacGia(tenTacGia).isPresent()) {
            throw new ResourceConflictException("Tác giả " + tenTacGia + " đã tồn tại");
        }
        TacGia newTacGia = new TacGia();
        newTacGia.setTenTacGia(tenTacGia);
        tacGiaRepository.save(newTacGia);
    }

    public TacGia changeTenTacGia(String slug, String newTenTacGia) {
        TacGia tacGia = getOneBySlug(slug);
        tacGia.setTenTacGia(newTenTacGia);
        return tacGiaRepository.save(tacGia);
    }

    public void deleteTacGia(String slug) {
        TacGia tacGia = tacGiaRepository.getBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        tacGiaRepository.delete(tacGia);
    }

}
