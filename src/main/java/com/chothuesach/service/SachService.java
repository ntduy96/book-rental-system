package com.chothuesach.service;

import com.chothuesach.config.AwsS3Config;
import com.chothuesach.dto.SachDto;
import com.chothuesach.dto.SachUpdateDto;
import com.chothuesach.exception.BookTitleExistsException;
import com.chothuesach.model.*;
import com.chothuesach.repository.DonGiaBanRepository;
import com.chothuesach.repository.SachRepository;
import com.chothuesach.repository.ThoiDiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class SachService {

	@Autowired
	private SachRepository sachRepository;

	@Autowired
	private TheLoaiService theLoaiService;
	
	@Autowired
	private TacGiaService tacGiaService;

	@Autowired
    private DonGiaBanRepository donGiaBanRepository;

	@Autowired
	private ThoiDiemRepository thoiDiemRepository;
	
	public Page<Sach> getAllSach(Pageable pageable) {
		return sachRepository.findAll(pageable);
	}

	public List<Sach> getSachByTheLoai(String theLoaiSlug) {
		TheLoai theLoai = theLoaiService.getTheLoaiBySlug(theLoaiSlug);
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
			newSach.setSachThuocTheLoai(mapTheLoai(new HashSet<>(), sachDto.getTheLoai()));
			newSach.setSachCuaTacGia(mapTacGia(new HashSet<>(), sachDto.getTacGia()));
			Sach saved = sachRepository.save(newSach);
			saved.setDonGiaBan(mapDonGiaBan(saved, new HashSet<>(), sachDto.getDonGiaBan()));
			return sachRepository.save(saved);
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
                sach.setSachThuocTheLoai(mapTheLoai(sach.getSachThuocTheLoai(), newTheLoai));
            }
            if (newTacGia != null) {
                sach.setSachCuaTacGia(mapTacGia(sach.getSachCuaTacGia(), newTacGia));
            }
            if (newDonGiaBan != null) {
                sach.setDonGiaBan(mapDonGiaBan(sach, sach.getDonGiaBan(), newDonGiaBan));
            }

            return sachRepository.save(sach);
        }
    }

    public void setAnhBia(String slug, MultipartFile file) {
		Sach sach = sachRepository.findOneBySlug(slug);
		try {
			// check if file type is image or not
			if (file.getContentType().contains("image/")) {
				if (sach.getAnhBia() != null) {
					AwsS3Config.deleteFile(new URI(sach.getAnhBia()));
				}
				// upload image to book-cover folder in s3 file bucket
				String anhBiaUrl = AwsS3Config.uploadFile("book-cover", file.getOriginalFilename(), file.getInputStream());
				sach.setAnhBia(anhBiaUrl);
				sachRepository.save(sach);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
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
	
	private Collection<TheLoai> mapTheLoai(Collection<TheLoai> theLoais, Set<String> tenTheLoais) {
		theLoais.removeAll(theLoais);
        for (String theLoai : tenTheLoais) {
            theLoais.add(theLoaiService.getTheLoaiByTenTheLoai(theLoai));
        }
		return theLoais;
	}
	
	private Collection<TacGia> mapTacGia(Collection<TacGia> tacGias, Set<String> tenTacGias) {
	    tacGias.removeAll(tacGias);
        for (String tacGia : tenTacGias) {
            tacGias.add(tacGiaService.getOneByTenTacGia(tacGia));
        }
		return tacGias;
	}
	
	private Collection<DonGiaBan> mapDonGiaBan(Sach sach, Collection<DonGiaBan> donGiaBans, Double donGiaBan) {
		DonGiaBan newDonGiaBan = new DonGiaBan();
		newDonGiaBan.setDonGia(donGiaBan);
		newDonGiaBan.setSach(sach);
		DonGiaBanId donGiaBanId = new DonGiaBanId();
		donGiaBanId.setMaSach(sach.getMaSach());
		ThoiDiem thoiDiem = thoiDiemRepository.save(new ThoiDiem());
		donGiaBanId.setThoiGian(thoiDiem.getThoiGian());
		newDonGiaBan.setDonGiaBanId(donGiaBanId);
		newDonGiaBan.setThoiDiem(thoiDiem);
		DonGiaBan savedDonGiaBan = donGiaBanRepository.save(newDonGiaBan);
		donGiaBans.add(savedDonGiaBan);
		return donGiaBans;
	}

}
