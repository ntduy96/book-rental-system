package com.chothuesach.service;

import com.chothuesach.config.AwsS3Config;
import com.chothuesach.dto.NguoiDungDto;
import com.chothuesach.exception.ResourceConflictException;
import com.chothuesach.exception.ResourceNotFoundException;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.model.Role;
import com.chothuesach.repository.NguoiDungRepository;
import com.chothuesach.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class NguoiDungService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	public NguoiDung getOneByUsername(String username) {
		Optional<NguoiDung> result = nguoiDungRepository.findOneByTenNguoiDung(username);
		if (result.isPresent()) {
			return result.get();
		}
		throw new ResourceNotFoundException("Can't find any Nguoi Dung matching tenNguoiDung: " + username);
	}

	public NguoiDung registerNewNguoiDung(NguoiDungDto nguoiDungDto) {
		
		if (emailExist(nguoiDungDto.getEmail())) {
			throw new ResourceConflictException("email " + nguoiDungDto.getEmail() + " đã tồn tại");
		}

		if (tenNguoiDungExist(nguoiDungDto.getTenNguoiDung())) {
			throw new ResourceConflictException("tên người dùng " + nguoiDungDto.getTenNguoiDung() + " đã tồn tại");
		}

		if (soCmndExist(nguoiDungDto.getSoCmnd())) {
			throw new ResourceConflictException("số CMND " + nguoiDungDto.getSoCmnd() + " đã tồn tại");
		}

		NguoiDung newNguoiDung = new NguoiDung();
		newNguoiDung.setTenNguoiDung(nguoiDungDto.getTenNguoiDung());
		newNguoiDung.setHoTenNguoiDung(nguoiDungDto.getHoTenNguoiDung());
		newNguoiDung.setDiaChiNguoiDung(nguoiDungDto.getDiaChiNguoiDung());
		newNguoiDung.setEmail(nguoiDungDto.getEmail());
		newNguoiDung.setSoCmnd(nguoiDungDto.getSoCmnd());
		newNguoiDung.setPassword(passwordEncoder.encode(nguoiDungDto.getPassword()));
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findOneByRoleName("ROLE_USER");// Assign ROLE_USER as default for new account
		roles.add(userRole);
		newNguoiDung.setRoles(roles);
		return nguoiDungRepository.save(newNguoiDung);

	}

	public boolean emailExist(String email) {
		return nguoiDungRepository.findOneByEmail(email) != null;
	}
	
	public boolean tenNguoiDungExist(String tenNguoiDung) {
		return nguoiDungRepository.findOneByTenNguoiDung(tenNguoiDung) != null;
	}
	
	public boolean soCmndExist(String soCmnd) {
		return nguoiDungRepository.findOneBySoCmnd(soCmnd) != null;
	}

	public void setAnhDaiDien(String tenNguoiDung, MultipartFile file) {
		NguoiDung nguoiDung = getOneByUsername(tenNguoiDung);
		try {
		    // check if file type is image or not
			if (file.getContentType().contains("image/")) {
				if (nguoiDung.getAnhDaiDien() != null) {
					AwsS3Config.deleteFile(new URI(nguoiDung.getAnhDaiDien()));
				}
				String avatarUrl = AwsS3Config.uploadFile("user-cover", file.getOriginalFilename(), file.getInputStream());
				nguoiDung.setAnhDaiDien(avatarUrl);
				nguoiDungRepository.save(nguoiDung);
			} else {
				throw new DataIntegrityViolationException("File type " + file.getContentType() + " is not supported");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
