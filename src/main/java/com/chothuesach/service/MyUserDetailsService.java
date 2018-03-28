package com.chothuesach.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chothuesach.model.MyUserPrincipal;
import com.chothuesach.model.NguoiDung;
import com.chothuesach.repository.NguoiDungRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoiDung = nguoiDungRepository.findOneByTenNguoiDung(username);
		if (nguoiDung == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(nguoiDung);
	}

}
