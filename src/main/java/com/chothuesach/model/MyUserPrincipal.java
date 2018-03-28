package com.chothuesach.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private NguoiDung nguoiDung;
	
	public MyUserPrincipal(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.nguoiDung.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.nguoiDung.getPassword();
	}

	@Override
	public String getUsername() {
		return this.nguoiDung.getTenNguoiDung();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.nguoiDung.isEnabled();
	}

}
