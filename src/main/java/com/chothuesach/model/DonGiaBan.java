package com.chothuesach.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.chothuesach.jsonview.SachView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class DonGiaBan {
	
	@EmbeddedId
	private DonGiaBanId donGiaBanId;

	@JsonView(SachView.CurrentPrice.class)
	public double donGia = 0;

	@ManyToOne
	@JoinColumn(name="MA_SACH", insertable=false, updatable=false)
	public Sach sach;
	
	@ManyToOne
	@JoinColumn(name="THOI_GIAN", insertable=false, updatable=false)
	public ThoiDiem thoiDiem;

	public DonGiaBanId getDonGiaBanId() {
		return donGiaBanId;
	}

	public void setDonGiaBanId(DonGiaBanId donGiaBanId) {
		this.donGiaBanId = donGiaBanId;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public ThoiDiem getThoiDiem() {
		return thoiDiem;
	}

	public void setThoiDiem(ThoiDiem thoiDiem) {
		this.thoiDiem = thoiDiem;
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
				oldSach.removeDonGiaBan(this);
			}
			if (newSach != null) {
				this.sach = newSach;
				this.sach.addDonGiaBan(this);
			}
		}
	}

	*//** @pdGenerated default parent getter *//*
	public ThoiDiem getThoiDiem() {
		return thoiDiem;
	}

	*//**
	 * @pdGenerated default parent setter
	 * @param newThoiDiem
	 *//*
	public void setThoiDiem(ThoiDiem newThoiDiem) {
		if (this.thoiDiem == null || !this.thoiDiem.equals(newThoiDiem)) {
			if (this.thoiDiem != null) {
				ThoiDiem oldThoiDiem = this.thoiDiem;
				this.thoiDiem = null;
				oldThoiDiem.removeDonGiaBan(this);
			}
			if (newThoiDiem != null) {
				this.thoiDiem = newThoiDiem;
				this.thoiDiem.addDonGiaBan(this);
			}
		}
	}
*/
}
