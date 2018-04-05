package com.chothuesach.model;

import com.chothuesach.jsonview.HoaDonView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class ChiTietHoaDon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private ChiTietHoaDonId chiTietHoaDonId;

	@JsonView(HoaDonView.Detailed.class)
	public int soLuongBan;
	
	@JsonView(HoaDonView.Detailed.class)
	public double donGiaBan;

	@ManyToOne
	@JoinColumn(name="MA_SACH", insertable=false, updatable=false)
	@JsonManagedReference
	@JsonView(HoaDonView.Detailed.class)
	public Sach sach;
	
	@ManyToOne
	@JoinColumn(name="MA_HOA_DON", insertable=false, updatable=false)
	@JsonIgnore
	public HoaDon hoaDon;

	public ChiTietHoaDonId getChiTietHoaDonId() {
		return chiTietHoaDonId;
	}

	public void setChiTietHoaDonId(ChiTietHoaDonId chiTietHoaDonId) {
		this.chiTietHoaDonId = chiTietHoaDonId;
	}

	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	/** @pdGenerated default parent getter *//*
	public Sach getSach() {
		return sach;
	}

	*//**
	 * @pdGenerated default parent setter
	 * @param newSach
	 *//*
	public void setSach(Sach newSach) {
		if (this.sach == null || !this.sach.equals(newSach)) {
			if (this.sach != null) {
				Sach oldSach = this.sach;
				this.sach = null;
				oldSach.removeChiTietHoaDon(this);
			}
			if (newSach != null) {
				this.sach = newSach;
				this.sach.addChiTietHoaDon(this);
			}
		}
	}

	*//** @pdGenerated default parent getter *//*
	public HoaDon getHoaDon() {
		return hoaDon;
	}

	*//**
	 * @pdGenerated default parent setter
	 * @param newHoaDon
	 *//*
	public void setHoaDon(HoaDon newHoaDon) {
		if (this.hoaDon == null || !this.hoaDon.equals(newHoaDon)) {
			if (this.hoaDon != null) {
				HoaDon oldHoaDon = this.hoaDon;
				this.hoaDon = null;
				oldHoaDon.removeChiTietHoaDon(this);
			}
			if (newHoaDon != null) {
				this.hoaDon = newHoaDon;
				this.hoaDon.addChiTietHoaDon(this);
			}
		}
	}
*/
}
