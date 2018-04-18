package com.chothuesach.model;

import com.chothuesach.helper.Slugify;
import com.chothuesach.jsonview.HoaDonView;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.jsonview.TacGiaView;
import com.chothuesach.jsonview.TheLoaiView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class Sach implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "book_id", strategy = "com.chothuesach.generator.BookIdGenerator")
	@GeneratedValue(generator = "book_id")
	@JsonView({SachView.Overview.class, TheLoaiView.Detailed.class, TacGiaView.Detailed.class, HoaDonView.Detailed.class})
	private String maSach;
	
	@NotNull
	@JsonView(SachView.Overview.class)
	private String tenSach;
	
	@NotNull
	@JsonView({SachView.Overview.class, HoaDonView.Detailed.class, TheLoaiView.Detailed.class})
	private String slug;
	
	@JsonView(SachView.Overview.class)
	private String anhBia;
	
	@JsonView(SachView.Detailed.class)
	private long soLuong = 0;
	
	@NotNull
	@JsonView(SachView.Detailed.class)
	private int soTrang;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(SachView.Detailed.class)
	private Date ngayXuatBan;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(SachView.Detailed.class)
	private Date ngayTao;

	@OneToMany(mappedBy="sach")
	@OrderBy("THOI_GIAN DESC")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonView(SachView.CurrentPrice.class)
	@Immutable
	private Collection<DonGiaBan> donGiaBan;
	
	@OneToMany(mappedBy="sach")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
	private Collection<ChiTietHoaDon> chiTietHoaDon;
	
	@OneToMany(mappedBy="sach")
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	private Collection<ChiTietPhieuNhap> chiTietPhieuNhap;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name="THUOC_THE_LOAI",
		joinColumns={@JoinColumn(name="MA_SACH")},
		inverseJoinColumns={@JoinColumn(name="MA_THE_LOAI")}
	)
//	@JsonIgnore
	@JsonView(SachView.Detailed.class)
	@JsonManagedReference
	@Immutable
	private Collection<TheLoai> sachThuocTheLoai;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name="CUA_TAC_GIA",
		joinColumns={@JoinColumn(name="MA_SACH")},
		inverseJoinColumns={@JoinColumn(name="MA_TAC_GIA")}
	)
//	@JsonIgnore
	@JsonView(SachView.Detailed.class)
	@Immutable
	private Collection<TacGia> sachCuaTacGia;
	
	@PrePersist
	@PreUpdate
	protected void onCreate() {
		ngayTao = new Date();
		slug = Slugify.toSlug(getTenSach());
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getAnhBia() {
		return anhBia;
	}

	public void setAnhBia(String anhBia) {
		this.anhBia = anhBia;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public Date getNgayXuatBan() {
		return ngayXuatBan;
	}

	public void setNgayXuatBan(Date ngayXuatBan) {
		this.ngayXuatBan = ngayXuatBan;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Collection<DonGiaBan> getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(Collection<DonGiaBan> donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public Collection<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(Collection<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public Collection<ChiTietPhieuNhap> getChiTietPhieuNhap() {
		return chiTietPhieuNhap;
	}

	public void setChiTietPhieuNhap(Collection<ChiTietPhieuNhap> chiTietPhieuNhap) {
		this.chiTietPhieuNhap = chiTietPhieuNhap;
	}

	public Collection<TheLoai> getSachThuocTheLoai() {
		return sachThuocTheLoai;
	}

	public void setSachThuocTheLoai(Collection<TheLoai> sachThuocTheLoai) {
		this.sachThuocTheLoai = sachThuocTheLoai;
	}

	public Collection<TacGia> getSachCuaTacGia() {
		return sachCuaTacGia;
	}

	public void setSachCuaTacGia(Collection<TacGia> sachCuaTacGia) {
		this.sachCuaTacGia = sachCuaTacGia;
	}

	/** @pdGenerated default getter *//*
	public Collection<DonGiaBan> getDonGiaBan() {
		if (donGiaBan == null)
			donGiaBan = new java.util.HashSet<DonGiaBan>();
		return donGiaBan;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<DonGiaBan> getIteratorDonGiaBan() {
		if (donGiaBan == null)
			donGiaBan = new java.util.HashSet<DonGiaBan>();
		return donGiaBan.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newDonGiaBan
	 *//*
	public void setDonGiaBan(Collection<DonGiaBan> newDonGiaBan) {
		removeAllDonGiaBan();
		for (Iterator<DonGiaBan> iter = newDonGiaBan.iterator(); iter.hasNext();)
			addDonGiaBan((DonGiaBan) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newDonGiaBan
	 *//*
	public void addDonGiaBan(DonGiaBan newDonGiaBan) {
		if (newDonGiaBan == null)
			return;
		if (this.donGiaBan == null)
			this.donGiaBan = new java.util.HashSet<DonGiaBan>();
		if (!this.donGiaBan.contains(newDonGiaBan)) {
			this.donGiaBan.add(newDonGiaBan);
			newDonGiaBan.setSach(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldDonGiaBan
	 *//*
	public void removeDonGiaBan(DonGiaBan oldDonGiaBan) {
		if (oldDonGiaBan == null)
			return;
		if (this.donGiaBan != null)
			if (this.donGiaBan.contains(oldDonGiaBan)) {
				this.donGiaBan.remove(oldDonGiaBan);
				oldDonGiaBan.setSach((Sach) null);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllDonGiaBan() {
		if (donGiaBan != null) {
			DonGiaBan oldDonGiaBan;
			for (Iterator<DonGiaBan> iter = getIteratorDonGiaBan(); iter.hasNext();) {
				oldDonGiaBan = (DonGiaBan) iter.next();
				iter.remove();
				oldDonGiaBan.setSach((Sach) null);
			}
		}
	}

	*//** @pdGenerated default getter *//*
	public Collection<ChiTietHoaDon> getChiTietHoaDon() {
		if (chiTietHoaDon == null)
			chiTietHoaDon = new java.util.HashSet<ChiTietHoaDon>();
		return chiTietHoaDon;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<ChiTietHoaDon> getIteratorChiTietHoaDon() {
		if (chiTietHoaDon == null)
			chiTietHoaDon = new java.util.HashSet<ChiTietHoaDon>();
		return chiTietHoaDon.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newChiTietHoaDon
	 *//*
	public void setChiTietHoaDon(Collection<ChiTietHoaDon> newChiTietHoaDon) {
		removeAllChiTietHoaDon();
		for (Iterator<ChiTietHoaDon> iter = newChiTietHoaDon.iterator(); iter.hasNext();)
			addChiTietHoaDon((ChiTietHoaDon) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newChiTietHoaDon
	 *//*
	public void addChiTietHoaDon(ChiTietHoaDon newChiTietHoaDon) {
		if (newChiTietHoaDon == null)
			return;
		if (this.chiTietHoaDon == null)
			this.chiTietHoaDon = new java.util.HashSet<ChiTietHoaDon>();
		if (!this.chiTietHoaDon.contains(newChiTietHoaDon)) {
			this.chiTietHoaDon.add(newChiTietHoaDon);
			newChiTietHoaDon.setSach(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldChiTietHoaDon
	 *//*
	public void removeChiTietHoaDon(ChiTietHoaDon oldChiTietHoaDon) {
		if (oldChiTietHoaDon == null)
			return;
		if (this.chiTietHoaDon != null)
			if (this.chiTietHoaDon.contains(oldChiTietHoaDon)) {
				this.chiTietHoaDon.remove(oldChiTietHoaDon);
				oldChiTietHoaDon.setSach((Sach) null);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllChiTietHoaDon() {
		if (chiTietHoaDon != null) {
			ChiTietHoaDon oldChiTietHoaDon;
			for (Iterator<ChiTietHoaDon> iter = getIteratorChiTietHoaDon(); iter.hasNext();) {
				oldChiTietHoaDon = (ChiTietHoaDon) iter.next();
				iter.remove();
				oldChiTietHoaDon.setSach((Sach) null);
			}
		}
	}

	*//** @pdGenerated default getter *//*
	public Collection<ChiTietPhieuNhap> getChiTietPhieuNhap() {
		if (chiTietPhieuNhap == null)
			chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		return chiTietPhieuNhap;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<ChiTietPhieuNhap> getIteratorChiTietPhieuNhap() {
		if (chiTietPhieuNhap == null)
			chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		return chiTietPhieuNhap.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newChiTietPhieuNhap
	 *//*
	public void setChiTietPhieuNhap(Collection<ChiTietPhieuNhap> newChiTietPhieuNhap) {
		removeAllChiTietPhieuNhap();
		for (Iterator<ChiTietPhieuNhap> iter = newChiTietPhieuNhap.iterator(); iter.hasNext();)
			addChiTietPhieuNhap((ChiTietPhieuNhap) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newChiTietPhieuNhap
	 *//*
	public void addChiTietPhieuNhap(ChiTietPhieuNhap newChiTietPhieuNhap) {
		if (newChiTietPhieuNhap == null)
			return;
		if (this.chiTietPhieuNhap == null)
			this.chiTietPhieuNhap = new java.util.HashSet<ChiTietPhieuNhap>();
		if (!this.chiTietPhieuNhap.contains(newChiTietPhieuNhap)) {
			this.chiTietPhieuNhap.add(newChiTietPhieuNhap);
			newChiTietPhieuNhap.setSach(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldChiTietPhieuNhap
	 *//*
	public void removeChiTietPhieuNhap(ChiTietPhieuNhap oldChiTietPhieuNhap) {
		if (oldChiTietPhieuNhap == null)
			return;
		if (this.chiTietPhieuNhap != null)
			if (this.chiTietPhieuNhap.contains(oldChiTietPhieuNhap)) {
				this.chiTietPhieuNhap.remove(oldChiTietPhieuNhap);
				oldChiTietPhieuNhap.setSach((Sach) null);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllChiTietPhieuNhap() {
		if (chiTietPhieuNhap != null) {
			ChiTietPhieuNhap oldChiTietPhieuNhap;
			for (Iterator<ChiTietPhieuNhap> iter = getIteratorChiTietPhieuNhap(); iter.hasNext();) {
				oldChiTietPhieuNhap = (ChiTietPhieuNhap) iter.next();
				iter.remove();
				oldChiTietPhieuNhap.setSach((Sach) null);
			}
		}
	}

	*//** @pdGenerated default getter *//*
	public Collection<TheLoai> getSachThuocTheLoai() {
		if (sachThuocTheLoai == null)
			sachThuocTheLoai = new java.util.HashSet<TheLoai>();
		return sachThuocTheLoai;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<TheLoai> getIteratorSachThuocTheLoai() {
		if (sachThuocTheLoai == null)
			sachThuocTheLoai = new java.util.HashSet<TheLoai>();
		return sachThuocTheLoai.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newSachThuocTheLoai
	 *//*
	public void setSachThuocTheLoai(Collection<TheLoai> newSachThuocTheLoai) {
		removeAllSachThuocTheLoai();
		for (Iterator<TheLoai> iter = newSachThuocTheLoai.iterator(); iter.hasNext();)
			addSachThuocTheLoai((TheLoai) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newTheLoai
	 *//*
	public void addSachThuocTheLoai(TheLoai newTheLoai) {
		if (newTheLoai == null)
			return;
		if (this.sachThuocTheLoai == null)
			this.sachThuocTheLoai = new java.util.HashSet<TheLoai>();
		if (!this.sachThuocTheLoai.contains(newTheLoai)) {
			this.sachThuocTheLoai.add(newTheLoai);
			newTheLoai.addSachThuocTheLoai(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldTheLoai
	 *//*
	public void removeSachThuocTheLoai(TheLoai oldTheLoai) {
		if (oldTheLoai == null)
			return;
		if (this.sachThuocTheLoai != null)
			if (this.sachThuocTheLoai.contains(oldTheLoai)) {
				this.sachThuocTheLoai.remove(oldTheLoai);
				oldTheLoai.removeSachThuocTheLoai(this);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllSachThuocTheLoai() {
		if (sachThuocTheLoai != null) {
			TheLoai oldTheLoai;
			for (Iterator<TheLoai> iter = getIteratorSachThuocTheLoai(); iter.hasNext();) {
				oldTheLoai = (TheLoai) iter.next();
				iter.remove();
				oldTheLoai.removeSachThuocTheLoai(this);
			}
		}
	}

	*//** @pdGenerated default getter *//*
	public Collection<TacGia> getSachCuaTacGia() {
		if (sachCuaTacGia == null)
			sachCuaTacGia = new java.util.HashSet<TacGia>();
		return sachCuaTacGia;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<TacGia> getIteratorSachCuaTacGia() {
		if (sachCuaTacGia == null)
			sachCuaTacGia = new java.util.HashSet<TacGia>();
		return sachCuaTacGia.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newSachCuaTacGia
	 *//*
	public void setSachCuaTacGia(Collection<TacGia> newSachCuaTacGia) {
		removeAllSachCuaTacGia();
		for (Iterator<TacGia> iter = newSachCuaTacGia.iterator(); iter.hasNext();)
			addSachCuaTacGia((TacGia) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newTacGia
	 *//*
	public void addSachCuaTacGia(TacGia newTacGia) {
		if (newTacGia == null)
			return;
		if (this.sachCuaTacGia == null)
			this.sachCuaTacGia = new java.util.HashSet<TacGia>();
		if (!this.sachCuaTacGia.contains(newTacGia)) {
			this.sachCuaTacGia.add(newTacGia);
			newTacGia.addSachCuaTacGia(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldTacGia
	 *//*
	public void removeSachCuaTacGia(TacGia oldTacGia) {
		if (oldTacGia == null)
			return;
		if (this.sachCuaTacGia != null)
			if (this.sachCuaTacGia.contains(oldTacGia)) {
				this.sachCuaTacGia.remove(oldTacGia);
				oldTacGia.removeSachCuaTacGia(this);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllSachCuaTacGia() {
		if (sachCuaTacGia != null) {
			TacGia oldTacGia;
			for (Iterator<TacGia> iter = getIteratorSachCuaTacGia(); iter.hasNext();) {
				oldTacGia = (TacGia) iter.next();
				iter.remove();
				oldTacGia.removeSachCuaTacGia(this);
			}
		}
	}*/

}
