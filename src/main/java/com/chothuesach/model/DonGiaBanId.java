package com.chothuesach.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DonGiaBanId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "MA_SACH")
	private String maSach;
	
	@Column(name = "THOI_GIAN")
	private Date thoiGian;
	
	public DonGiaBanId() {}
	
	public DonGiaBanId(String maSach, Date thoiGian) {
		this.setMaSach(maSach);
		this.setThoiGian(thoiGian);
	}
	
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        DonGiaBanId that = (DonGiaBanId) o;
        return Objects.equals(maSach, that.maSach) && 
               Objects.equals(thoiGian, that.thoiGian);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(maSach, thoiGian);
    }

}
