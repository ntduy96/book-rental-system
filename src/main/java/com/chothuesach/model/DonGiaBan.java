package com.chothuesach.model;

import com.chothuesach.jsonview.SachView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DonGiaBan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long maDonGiaBan;

	@JsonView({SachView.Overview.class, SachView.CurrentPrice.class})
	private double donGia = 0;

	@ManyToOne
	@JoinColumn(name="MA_SACH")
	@NotNull
	private Sach sach;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date ngayTao = new Date();

	public Long getMaDonGiaBan() {
		return maDonGiaBan;
	}

	public void setMaDonGiaBan(Long maDonGiaBan) {
		this.maDonGiaBan = maDonGiaBan;
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

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
}
