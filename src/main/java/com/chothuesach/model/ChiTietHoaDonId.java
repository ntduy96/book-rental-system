package com.chothuesach.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "MA_SACH")
	private String maSach;
	
	@Column(name = "MA_HOA_DON")
	private String maHoaDon;
	
	public ChiTietHoaDonId() {}

	public ChiTietHoaDonId(String maSach, String maHoaDon) {
		super();
		this.maSach = maSach;
		this.maHoaDon = maHoaDon;
	}

}
