package com.chothuesach.service;

import com.chothuesach.dto.SachDto;
import com.chothuesach.exception.BookTitleExistsException;
import com.chothuesach.model.DonGiaBan;
import com.chothuesach.model.Sach;
import com.chothuesach.model.TacGia;
import com.chothuesach.model.TheLoai;
import com.chothuesach.repository.SachRepository;
import com.chothuesach.repository.TacGiaRepository;
import com.chothuesach.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class SachService {

	@Autowired
	private SachRepository sachRepository;

	@Autowired
	private TheLoaiRepository theLoaiRepository;
	
	@Autowired
	private TacGiaRepository tacGiaRepository;
	
	public Page<Sach> getAllSach(Pageable pageable) {
//		Sort sort = new Sort(Sort.Direction.DESC, "ngayTao");
//		PageRequest page = PageRequest.of(pageNumber - 1, 5);
		return sachRepository.findAll(pageable);
	}

	public List<Sach> getSachByTheLoai(String theLoaiSlug) {
		TheLoai theLoai = theLoaiRepository.getBySlug(theLoaiSlug);
		return sachRepository.findBySachThuocTheLoai(theLoai);
	}
	
	public List<Sach> searchByTenSach(String tenSach, Pageable pageable) {
		return sachRepository.findByTenSachContains(tenSach, pageable);
	}

	public Sach getOneByMaSach(String maSach) {
		return sachRepository.findById(maSach).get();
	}

	public Sach getOneBySlug(String slug) {
		return sachRepository.findOneBySlug(slug);
	}

	public Sach getOneByTenSach(String tenSach) {
		return sachRepository.findOneByTenSach(tenSach);
	}

	public boolean tenSachExist(String tenSach) {
		return sachRepository.findOneByTenSach(tenSach) != null ? true : false;
	}
	
	public Sach createNewSach(SachDto sachDto) {
		if (tenSachExist(sachDto.getTenSach())) {
			throw new BookTitleExistsException(sachDto.getTenSach());
		} else {
			Sach newSach = new Sach();
			newSach.setTenSach(sachDto.getTenSach());
			newSach.setAnhBia(sachDto.getAnhBia());
			newSach.setNgayXuatBan(sachDto.getNgayXuatBan());
			newSach.setSoLuong(sachDto.getSoLuong());
			newSach.setSoTrang(sachDto.getSoTrang());
			newSach.setSachThuocTheLoai(mapTheLoai(sachDto.getTheLoai()));
			newSach.setSachCuaTacGia(mapTacGia(sachDto.getTacGia()));
			newSach.setDonGiaBan(mapDonGiaBan(sachDto.getDonGiaBan()));
			return sachRepository.save(newSach);
		}
	}
	
	public void changeSoLuongSach(String slug, long newSoLuong) {
		sachRepository.updateSoLuongSachByMaSach(newSoLuong, slug);
	}
	
	public void deleteSachByMaSach(String maSach) {
		sachRepository.deleteByMaSach(maSach);
	}
	
	public void deleteSachBySlug(String slug) {
		sachRepository.deleteBySlug(slug);
	}
	
	private Set<TheLoai> mapTheLoai(Set<Long> maTheLoais) {
		Set<TheLoai> theLoais = new HashSet<TheLoai>();
		Iterator<Long> maTheLoaiIterator = maTheLoais.iterator();
		while (maTheLoaiIterator.hasNext()) {
			Long theLoai = (Long) maTheLoaiIterator.next();
			theLoais.add(theLoaiRepository.findById(theLoai).get());
		}
		return theLoais;
	}
	
	private Set<TacGia> mapTacGia(Set<String> maTacGias) {
		Set<TacGia> tacGias = new HashSet<TacGia>();
		Iterator<String> maTacGiaIterator = maTacGias.iterator();
		while (maTacGiaIterator.hasNext()) {
			String tacGia = (String) maTacGiaIterator.next();
			tacGias.add(tacGiaRepository.findById(tacGia).get());
		}
		return tacGias;
	}
	
	private Set<DonGiaBan> mapDonGiaBan(Double donGiaBan) {
		Set<DonGiaBan> donGiaBans = new HashSet<DonGiaBan>();
		DonGiaBan newDonGiaBan = new DonGiaBan();
		newDonGiaBan.setDonGia(donGiaBan);
		donGiaBans.add(newDonGiaBan);
		return donGiaBans;
	}

}
