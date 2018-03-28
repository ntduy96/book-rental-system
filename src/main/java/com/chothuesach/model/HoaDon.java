package com.chothuesach.model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.chothuesach.jsonview.HoaDonView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class HoaDon {

	@Id
	@GenericGenerator(name = "invoice_id", strategy = "com.chothuesach.generator.InvoiceIdGenerator")
	@GeneratedValue(generator = "invoice_id")
	@JsonView(HoaDonView.Overview.class)
	public String maHoaDon;

	@NotNull
	@JsonView(HoaDonView.Detailed.class)
	public double giaTri;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(HoaDonView.Detailed.class)
	public Date ngayLapHoaDon;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(HoaDonView.Detailed.class)
	public Date ngayThanhToan;

	@OneToMany(mappedBy = "hoaDon")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonView(HoaDonView.Detailed.class)
	public Collection<ChiTietHoaDon> chiTietHoaDon;

	@ManyToOne(/*fetch=FetchType.LAZY*/)
	@JoinColumn(name = "MA_NHAN_VIEN")
	@JsonView(HoaDonView.Detailed.class)
	public NhanVien nhanVien;

	@ManyToOne(/*fetch=FetchType.LAZY*/)
	@JoinColumn(name = "MA_KHACH_HANG")
	@JsonView(HoaDonView.Detailed.class)
	public NguoiDung khachHang;

	@PrePersist
	protected void onCreate() {
		ngayLapHoaDon = new Date();
		giaTri = 0;
		chiTietHoaDon.forEach((row) -> giaTri += row.donGiaBan * row.soLuongBan);
	}

	/** @pdGenerated default getter */
	public Collection<ChiTietHoaDon> getChiTietHoaDon() {
		if (chiTietHoaDon == null)
			chiTietHoaDon = new java.util.HashSet<ChiTietHoaDon>();
		return chiTietHoaDon;
	}

	/**
	 * @pdGenerated default setter
	 * @param newChiTietHoaDon
	 */
	public void setChiTietHoaDon(Collection<ChiTietHoaDon> newChiTietHoaDon) {
		removeAllChiTietHoaDon();
		for (Iterator<ChiTietHoaDon> iter = newChiTietHoaDon.iterator(); iter.hasNext();)
			addChiTietHoaDon((ChiTietHoaDon) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newChiTietHoaDon
	 */
	public void addChiTietHoaDon(ChiTietHoaDon newChiTietHoaDon) {
		if (newChiTietHoaDon == null)
			return;
		if (this.chiTietHoaDon == null)
			this.chiTietHoaDon = new java.util.HashSet<ChiTietHoaDon>();
		if (!this.chiTietHoaDon.contains(newChiTietHoaDon)) {
			this.chiTietHoaDon.add(newChiTietHoaDon);
			newChiTietHoaDon.setHoaDon(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldChiTietHoaDon
	 */
	public void removeChiTietHoaDon(ChiTietHoaDon oldChiTietHoaDon) {
		if (oldChiTietHoaDon == null)
			return;
		if (this.chiTietHoaDon != null)
			if (this.chiTietHoaDon.contains(oldChiTietHoaDon)) {
				this.chiTietHoaDon.remove(oldChiTietHoaDon);
				oldChiTietHoaDon.setHoaDon((HoaDon) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllChiTietHoaDon() {
		if (chiTietHoaDon != null) {
			ChiTietHoaDon oldChiTietHoaDon;
			for (Iterator<ChiTietHoaDon> iter = getChiTietHoaDon().iterator(); iter.hasNext();) {
				oldChiTietHoaDon = (ChiTietHoaDon) iter.next();
				iter.remove();
				oldChiTietHoaDon.setHoaDon((HoaDon) null);
			}
		}
	}

	/** @pdGenerated default parent getter */
	public NguoiDung getKhachHang() {
		return khachHang;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newNguoiDung
	 */
	public void setKhachHang(NguoiDung newNguoiDung) {
		if (this.khachHang == null || !this.khachHang.equals(newNguoiDung)) {
			if (this.khachHang != null) {
				NguoiDung oldNguoiDung = this.khachHang;
				this.khachHang = null;
				oldNguoiDung.removeHoaDon(this);
			}
			if (newNguoiDung != null) {
				this.khachHang = newNguoiDung;
				this.khachHang.addHoaDon(this);
			}
		}
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
				oldNhanVien.removeHoaDon(this);
			}
			if (newNhanVien != null) {
				this.nhanVien = newNhanVien;
				this.nhanVien.addHoaDon(this);
			}
		}
	}

}
