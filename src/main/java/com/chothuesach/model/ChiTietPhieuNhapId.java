package com.chothuesach.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChiTietPhieuNhapId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "MA_SACH", nullable=false, updatable=false)
	private String maSach;
	
	@Column(name = "MA_PHIEU_NHAP", nullable=false, updatable=false)
	private String maPhieuNhap;

	public ChiTietPhieuNhapId() {}
	
	public ChiTietPhieuNhapId(String maSach, String maPhieuNhap) {
		super();
		this.maSach = maSach;
		this.maPhieuNhap = maPhieuNhap;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        ChiTietPhieuNhapId that = (ChiTietPhieuNhapId) o;
        return Objects.equals(maSach, that.maSach) && 
               Objects.equals(maPhieuNhap, that.maPhieuNhap);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(maSach, maPhieuNhap);
    }

}
