package com.chothuesach.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class PhieuChi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	public String maPhieuChi;

	@Temporal(TemporalType.TIMESTAMP)
	public Date ngayLapPhieuChi;

	@NotNull
	public double soTienThanhToan;

	@ManyToOne
	@JoinColumn(name = "MA_NHAN_VIEN")
	public NhanVien nhanVien;

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
