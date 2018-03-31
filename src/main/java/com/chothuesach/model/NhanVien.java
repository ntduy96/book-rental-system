package com.chothuesach.model;

import com.chothuesach.jsonview.NhanVienView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Iterator;

@Entity
@PrimaryKeyJoinColumn(name = "MA_NHAN_VIEN")
public class NhanVien extends NguoiDung {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "nhanVien")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private java.util.Collection<PhieuChi> phieuChi;

	@OneToMany(mappedBy = "nhanVien")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
	private java.util.Collection<HoaDon> hoaDon;

	@OneToMany(mappedBy = "nhanVien")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private java.util.Collection<PhieuNhapHang> phieuNhapHang;

	@ManyToOne
	@JoinColumn(name = "MA_CHUC_VU")
	@JsonView(NhanVienView.Detailed.class)
    private ChucVu chucVu;

	/** @pdGenerated default getter */
	public java.util.Collection<PhieuChi> getPhieuChi() {
		if (phieuChi == null)
			phieuChi = new java.util.HashSet<PhieuChi>();
		return phieuChi;
	}

	/**
	 * @pdGenerated default setter
	 * @param newPhieuChi
	 */
	public void setPhieuChi(java.util.Collection<PhieuChi> newPhieuChi) {
		removeAllPhieuChi();
		for (Iterator<PhieuChi> iter = newPhieuChi.iterator(); iter.hasNext();)
			addPhieuChi((PhieuChi) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newPhieuChi
	 */
	public void addPhieuChi(PhieuChi newPhieuChi) {
		if (newPhieuChi == null)
			return;
		if (this.phieuChi == null)
			this.phieuChi = new java.util.HashSet<PhieuChi>();
		if (!this.phieuChi.contains(newPhieuChi)) {
			this.phieuChi.add(newPhieuChi);
			newPhieuChi.setNhanVien(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldPhieuChi
	 */
	public void removePhieuChi(PhieuChi oldPhieuChi) {
		if (oldPhieuChi == null)
			return;
		if (this.phieuChi != null)
			if (this.phieuChi.contains(oldPhieuChi)) {
				this.phieuChi.remove(oldPhieuChi);
				oldPhieuChi.setNhanVien((NhanVien) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllPhieuChi() {
		if (phieuChi != null) {
			PhieuChi oldPhieuChi;
			for (Iterator<PhieuChi> iter = getPhieuChi().iterator(); iter.hasNext();) {
				oldPhieuChi = (PhieuChi) iter.next();
				iter.remove();
				oldPhieuChi.setNhanVien((NhanVien) null);
			}
		}
	}

	/** @pdGenerated default getter */
	public java.util.Collection<HoaDon> getHoaDon() {
		if (hoaDon == null)
			hoaDon = new java.util.HashSet<HoaDon>();
		return hoaDon;
	}

	/**
	 * @pdGenerated default setter
	 * @param newHoaDon
	 */
	public void setHoaDon(java.util.Collection<HoaDon> newHoaDon) {
		removeAllHoaDon();
		for (Iterator<HoaDon> iter = newHoaDon.iterator(); iter.hasNext();)
			addHoaDon((HoaDon) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newHoaDon
	 */
	public void addHoaDon(HoaDon newHoaDon) {
		if (newHoaDon == null)
			return;
		if (this.hoaDon == null)
			this.hoaDon = new java.util.HashSet<HoaDon>();
		if (!this.hoaDon.contains(newHoaDon)) {
			this.hoaDon.add(newHoaDon);
			newHoaDon.setNhanVien(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldHoaDon
	 */
	public void removeHoaDon(HoaDon oldHoaDon) {
		if (oldHoaDon == null)
			return;
		if (this.hoaDon != null)
			if (this.hoaDon.contains(oldHoaDon)) {
				this.hoaDon.remove(oldHoaDon);
				oldHoaDon.setNhanVien((NhanVien) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllHoaDon() {
		if (hoaDon != null) {
			HoaDon oldHoaDon;
			for (Iterator<HoaDon> iter = getHoaDon().iterator(); iter.hasNext();) {
				oldHoaDon = (HoaDon) iter.next();
				iter.remove();
				oldHoaDon.setNhanVien((NhanVien) null);
			}
		}
	}

	/** @pdGenerated default getter */
	public java.util.Collection<PhieuNhapHang> getPhieuNhapHang() {
		if (phieuNhapHang == null)
			phieuNhapHang = new java.util.HashSet<PhieuNhapHang>();
		return phieuNhapHang;
	}

	/**
	 * @pdGenerated default setter
	 * @param newPhieuNhapHang
	 */
	public void setPhieuNhapHang(java.util.Collection<PhieuNhapHang> newPhieuNhapHang) {
		removeAllPhieuNhapHang();
		for (Iterator<PhieuNhapHang> iter = newPhieuNhapHang.iterator(); iter.hasNext();)
			addPhieuNhapHang((PhieuNhapHang) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newPhieuNhapHang
	 */
	public void addPhieuNhapHang(PhieuNhapHang newPhieuNhapHang) {
		if (newPhieuNhapHang == null)
			return;
		if (this.phieuNhapHang == null)
			this.phieuNhapHang = new java.util.HashSet<PhieuNhapHang>();
		if (!this.phieuNhapHang.contains(newPhieuNhapHang)) {
			this.phieuNhapHang.add(newPhieuNhapHang);
			newPhieuNhapHang.setNhanVien(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldPhieuNhapHang
	 */
	public void removePhieuNhapHang(PhieuNhapHang oldPhieuNhapHang) {
		if (oldPhieuNhapHang == null)
			return;
		if (this.phieuNhapHang != null)
			if (this.phieuNhapHang.contains(oldPhieuNhapHang)) {
				this.phieuNhapHang.remove(oldPhieuNhapHang);
				oldPhieuNhapHang.setNhanVien((NhanVien) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllPhieuNhapHang() {
		if (phieuNhapHang != null) {
			PhieuNhapHang oldPhieuNhapHang;
			for (Iterator<PhieuNhapHang> iter = getPhieuNhapHang().iterator(); iter.hasNext();) {
				oldPhieuNhapHang = (PhieuNhapHang) iter.next();
				iter.remove();
				oldPhieuNhapHang.setNhanVien((NhanVien) null);
			}
		}
	}

	/** @pdGenerated default parent getter */
	public ChucVu getChucVu() {
		return chucVu;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newChucVu
	 */
	public void setChucVu(ChucVu newChucVu) {
		if (this.chucVu == null || !this.chucVu.equals(newChucVu)) {
			if (this.chucVu != null) {
				ChucVu oldChucVu = this.chucVu;
				this.chucVu = null;
				oldChucVu.removeNhanVien(this);
			}
			if (newChucVu != null) {
				this.chucVu = newChucVu;
				this.chucVu.addNhanVien(this);
			}
		}
	}

}
