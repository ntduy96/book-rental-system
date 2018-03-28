package com.chothuesach.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chothuesach.dto.NguoiDungDto;
import com.chothuesach.exception.EmailExistsException;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.model.Role;
import com.chothuesach.repository.NguoiDungRepository;
import com.chothuesach.repository.RoleRepository;

@Service
public class NguoiDungService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	public NguoiDung getOneByUsername(String username) {
		return nguoiDungRepository.findOneByTenNguoiDung(username);
	}

	public NguoiDung registerNewNguoiDung(NguoiDungDto nguoiDungDto) {
		
		if (emailExist(nguoiDungDto.getEmail())) {
			throw new EmailExistsException(nguoiDungDto.getEmail());
		} else {
			NguoiDung newNguoiDung = new NguoiDung();
			newNguoiDung.setTenNguoiDung(nguoiDungDto.getTenNguoiDung());
			newNguoiDung.setHoTenNguoiDung(nguoiDungDto.getHoTenNguoiDung());
			newNguoiDung.setDiaChiNguoiDung(nguoiDungDto.getDiaChiNguoiDung());
			newNguoiDung.setEmail(nguoiDungDto.getEmail());
			newNguoiDung.setSoCmnd(nguoiDungDto.getSoCmnd());
			newNguoiDung.setPassword(passwordEncoder.encode(nguoiDungDto.getPassword()));
			Set<Role> roles = new HashSet<Role>();
			Role userRole = roleRepository.findOneByRoleName("ROLE_USER");// Assign ROLE_USER as default for new account
			roles.add(userRole);
			newNguoiDung.setRoles(roles);
			return nguoiDungRepository.save(newNguoiDung);
		}

	}

	public boolean emailExist(String email) {
		return nguoiDungRepository.findOneByEmail(email) != null ? true : false;
	}
	
	public boolean tenNguoiDungExist(String tenNguoiDung) {
		return nguoiDungRepository.findOneByTenNguoiDung(tenNguoiDung) != null ? true : false;
	}
	
	public boolean soCmndExist(String soCmnd) {
		return nguoiDungRepository.findOneBySoCmnd(soCmnd) != null ? true : false;
	}

}
