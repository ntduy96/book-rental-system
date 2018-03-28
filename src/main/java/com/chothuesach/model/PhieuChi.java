package com.chothuesach.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class PhieuChi {

	@Id
	public String maPhieuChi;

	@Temporal(TemporalType.TIMESTAMP)
	public Date ngayLapPhieuChi;

	@NotNull
	public double soTienThanhToan;

	@ManyToOne
	@JoinColumn(name = "MA_NHAN_VIEN")
	public NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "MA_PHIEU_NHAP")
	public PhieuNhapHang phieuNhapHang;

	@PrePersist
	protected void onCreate() {
		ngayLapPhieuChi = new Date();
	}

	/** @pdGenerated default parent getter */
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newNhanVien
	 */
	public void setNhanVien(NhanVien newNhanVien) {
		if (this.nhanVien == null || !this.nhanVien.equals(newNhanVien)) {
			if (this.nhanVien != null) {
				NhanVien oldNhanVien = this.nhanVien;
				this.nhanVien = null;
				oldNhanVien.removePhieuChi(this);
			}
			if (newNhanVien != null) {
				this.nhanVien = newNhanVien;
				this.nhanVien.addPhieuChi(this);
			}
		}
	}

}
