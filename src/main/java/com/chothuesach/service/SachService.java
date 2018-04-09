package com.chothuesach.service;

import com.chothuesach.dto.SachDto;
import com.chothuesach.dto.SachUpdateDto;
import com.chothuesach.exception.BookTitleExistsException;
import com.chothuesach.model.DonGiaBan;
import com.chothuesach.model.Sach;
import com.chothuesach.model.TacGia;
import com.chothuesach.model.TheLoai;
import com.chothuesach.repository.DonGiaBanRepository;
import com.chothuesach.repository.SachRepository;
import com.chothuesach.repository.TacGiaRepository;
import com.chothuesach.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
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

	@Autowired
    private DonGiaBanRepository donGiaBanRepository;
	
	public Page<Sach> getAllSach(Pageable pageable) {
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

	public Sach updateSach(String slug, SachUpdateDto sachUpdateDto) {
        if (tenSachExist(sachUpdateDto.getTenSach())) {
            throw new BookTitleExistsException(sachUpdateDto.getTenSach());
        } else {
            Sach sach = sachRepository.findOneBySlug(slug);
            String newAnhBia = sachUpdateDto.getAnhBia();
            String newTenSach = sachUpdateDto.getTenSach();
            Long newSoLuong = sachUpdateDto.getSoLuong();
            Integer newSoTrang = sachUpdateDto.getSoTrang();
            Date newNgayXuatBan = sachUpdateDto.getNgayXuatBan();
            Set<String> newTheLoai = sachUpdateDto.getTheLoai();
            Set<String> newTacGia = sachUpdateDto.getTacGia();
            Double newDonGiaBan = sachUpdateDto.getDonGiaBan();

            if (newAnhBia != null) {
                sach.setAnhBia(newAnhBia);
            }
            if (newTenSach != null) {
                sach.setTenSach(newTenSach);
            }
            if (newSoLuong != null) {
                sach.setSoTrang(newSoTrang);
            }
            if (newNgayXuatBan != null) {
                sach.setNgayXuatBan(newNgayXuatBan);
            }
            if (newTheLoai != null) {
                sach.setSachThuocTheLoai(mapTheLoai(newTheLoai));
            }
            if (newTacGia != null) {
                sach.setSachCuaTacGia(mapTacGia(newTacGia));
            }

            return sachRepository.save(sach);
        }
    }
	
	public void changeSoLuongSach(String slug, long newSoLuong) {
		sachRepository.updateSoLuongSachByMaSach(newSoLuong, slug);
	}

	public DonGiaBan getLatestPrice(String slug) {
	    return donGiaBanRepository.getLatestDonGiaBanOfSach(sachRepository.findOneBySlug(slug).getMaSach());
    }
	
	public void deleteSachByMaSach(String maSach) {
		sachRepository.deleteByMaSach(maSach);
	}
	
	public void deleteSachBySlug(String slug) {
		sachRepository.deleteBySlug(slug);
	}
	
	private Set<TheLoai> mapTheLoai(Set<String> tenTheLoais) {
		Set<TheLoai> theLoais = new HashSet<>();
        for (String theLoai : tenTheLoais) {
            theLoais.add(theLoaiRepository.getByTenTheLoai(theLoai));
        }
		return theLoais;
	}
	
	private Set<TacGia> mapTacGia(Set<String> tenTacGias) {
		Set<TacGia> tacGias = new HashSet<>();
        for (String tacGia : tenTacGias) {
            tacGias.add(tacGiaRepository.getByTenTacGia(tacGia));
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
