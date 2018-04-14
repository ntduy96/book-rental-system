package com.chothuesach.service;

import com.chothuesach.model.MyUserPrincipal;
import com.chothuesach.model.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private NguoiDungService nguoiDungService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoiDung = nguoiDungService.getOneByUsername(username);

		return new MyUserPrincipal(nguoiDung);
	}

}
