package com.chothuesach.model;

import com.chothuesach.jsonview.PhieuNhapHangView;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Entity
public class PhieuNhapHang implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(PhieuNhapHangView.Overview.class)
	public String maPhieuNhap;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(PhieuNhapHangView.Detailed.class)
	public Date ngayLapPhieuNhap;

	@NotNull
	@JsonView(PhieuNhapHangView.Detailed.class)
	public double tongTienNhap;

	@OneToMany(mappedBy = "phieuNhapHang")
//	@Fetch(FetchMode.JOIN)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonView(PhieuNhapHangView.Detailed.class)
	public Collection<ChiTietPhieuNhap> chiTietPhieuNhap;

	@ManyToOne
	@JoinColumn(name = "MA_PHIEU_CHI")
	@JsonView(PhieuNhapHangView.Detailed.class)
	public PhieuChi phieuChi;

	@ManyToOne
	@JoinColumn(name = "MA_NHAN_VIEN")
	@JsonView(PhieuNhapHangView.Detailed.class)
	public NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "MA_NPP")
	@JsonView(PhieuNhapHangView.Detailed.class)
	public NhaPhanPhoi nhaPhanPhoi;

	@PrePersist
	protected void onCreate() {
		ngayLapPhieuNhap = new Date();
	}

	/** @pdGenerated default getter */
	public Collection<ChiTietPhieuNhap> getChiTietPhieuNhap() {
		if (chiTietPhieuNhap == null)
			chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		return chiTietPhieuNhap;
	}

	/** @pdGenerated default iterator getter */
	public Iterator<ChiTietPhieuNhap> getIteratorChiTietPhieuNhap() {
		if (chiTietPhieuNhap == null)
			chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		return chiTietPhieuNhap.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newChiTietPhieuNhap
	 */
	public void setChiTietPhieuNhap(Collection<ChiTietPhieuNhap> newChiTietPhieuNhap) {
		removeAllChiTietPhieuNhap();
		for (Iterator<ChiTietPhieuNhap> iter = newChiTietPhieuNhap.iterator(); iter.hasNext();)
			addChiTietPhieuNhap((ChiTietPhieuNhap) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newChiTietPhieuNhap
	 */
	public void addChiTietPhieuNhap(ChiTietPhieuNhap newChiTietPhieuNhap) {
		if (newChiTietPhieuNhap == null)
			return;
		if (this.chiTietPhieuNhap == null)
			this.chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		if (!this.chiTietPhieuNhap.contains(newChiTietPhieuNhap)) {
			this.chiTietPhieuNhap.add(newChiTietPhieuNhap);
			newChiTietPhieuNhap.setPhieuNhapHang(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldChiTietPhieuNhap
	 */
	public void removeChiTietPhieuNhap(ChiTietPhieuNhap oldChiTietPhieuNhap) {
		if (oldChiTietPhieuNhap == null)
			return;
		if (this.chiTietPhieuNhap != null)
			if (this.chiTietPhieuNhap.contains(oldChiTietPhieuNhap)) {
				this.chiTietPhieuNhap.remove(oldChiTietPhieuNhap);
				oldChiTietPhieuNhap.setPhieuNhapHang((PhieuNhapHang) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllChiTietPhieuNhap() {
		if (chiTietPhieuNhap != null) {
			ChiTietPhieuNhap oldChiTietPhieuNhap;
			for (Iterator<ChiTietPhieuNhap> iter = getIteratorChiTietPhieuNhap(); iter.hasNext();) {
				oldChiTietPhieuNhap = (ChiTietPhieuNhap) iter.next();
				iter.remove();
				oldChiTietPhieuNhap.setPhieuNhapHang((PhieuNhapHang) null);
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
				oldNhanVien.removePhieuNhapHang(this);
			}
			if (newNhanVien != null) {
				this.nhanVien = newNhanVien;
				this.nhanVien.addPhieuNhapHang(this);
			}
		}
	}

	/** @pdGenerated default parent getter */
	public NhaPhanPhoi getNhaPhanPhoi() {
		return nhaPhanPhoi;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newNhaPhanPhoi
	 */
	public void setNhaPhanPhoi(NhaPhanPhoi newNhaPhanPhoi) {
		if (this.nhaPhanPhoi == null || !this.nhaPhanPhoi.equals(newNhaPhanPhoi)) {
			if (this.nhaPhanPhoi != null) {
				NhaPhanPhoi oldNhaPhanPhoi = this.nhaPhanPhoi;
				this.nhaPhanPhoi = null;
				oldNhaPhanPhoi.removePhieuNhapHang(this);
			}
			if (newNhaPhanPhoi != null) {
				this.nhaPhanPhoi = newNhaPhanPhoi;
				this.nhaPhanPhoi.addPhieuNhapHang(this);
			}
		}
	}

}
