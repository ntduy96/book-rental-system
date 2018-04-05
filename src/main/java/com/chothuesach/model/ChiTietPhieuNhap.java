package com.chothuesach.model;

import com.chothuesach.jsonview.PhieuNhapHangView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class ChiTietPhieuNhap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ChiTietPhieuNhapId chiTietPhieuNhapId;
	
	@JsonView(PhieuNhapHangView.Detailed.class)
	public int soLuongNhap;

	@ManyToOne
	@JoinColumn(name="MA_SACH", insertable=false, updatable=false)
	@JsonView(PhieuNhapHangView.Detailed.class)
	public Sach sach;

	@ManyToOne
	@JoinColumn(name = "MA_PHIEU_NHAP", insertable=false, updatable=false)
	public PhieuNhapHang phieuNhapHang;

	public ChiTietPhieuNhapId getChiTietPhieuNhapId() {
		return chiTietPhieuNhapId;
	}

	public void setChiTietPhieuNhapId(ChiTietPhieuNhapId chiTietPhieuNhapId) {
		this.chiTietPhieuNhapId = chiTietPhieuNhapId;
	}

	public int getSoLuongNhap() {
		return soLuongNhap;
	}

	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public PhieuNhapHang getPhieuNhapHang() {
		return phieuNhapHang;
	}

	public void setPhieuNhapHang(PhieuNhapHang phieuNhapHang) {
		this.phieuNhapHang = phieuNhapHang;
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
				oldSach.removeChiTietPhieuNhap(this);
			}
			if (newSach != null) {
				this.sach = newSach;
				this.sach.addChiTietPhieuNhap(this);
			}
		}
	}

	*//** @pdGenerated default parent getter *//*
	public PhieuNhapHang getPhieuNhapHang() {
		return phieuNhapHang;
	}

	*//**
	 * @pdGenerated default parent setter
	 * @param newPhieuNhapHang
	 *//*
	public void setPhieuNhapHang(PhieuNhapHang newPhieuNhapHang) {
		if (this.phieuNhapHang == null || !this.phieuNhapHang.equals(newPhieuNhapHang)) {
			if (this.phieuNhapHang != null) {
				PhieuNhapHang oldPhieuNhapHang = this.phieuNhapHang;
				this.phieuNhapHang = null;
				oldPhieuNhapHang.removeChiTietPhieuNhap(this);
			}
			if (newPhieuNhapHang != null) {
				this.phieuNhapHang = newPhieuNhapHang;
				this.phieuNhapHang.addChiTietPhieuNhap(this);
			}
		}
	}
*/
}
