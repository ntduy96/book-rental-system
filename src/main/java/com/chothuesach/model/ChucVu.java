package com.chothuesach.model;

import com.chothuesach.jsonview.ChucVuView;
import com.chothuesach.jsonview.NhanVienView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

@Entity
public class ChucVu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ChucVuView.Overview.class, NhanVienView.Detailed.class})
	private Short maChucVu;

	@NotNull
	@JsonView({ChucVuView.Overview.class, NhanVienView.Overview.class, NhanVienView.Detailed.class})
	private String tenChucVu;

	@OneToMany(mappedBy = "chucVu")
	@JsonView(ChucVuView.Detailed.class)
	private Collection<NhanVien> nhanVien;

	public Short getMaChucVu() {
		return maChucVu;
	}

	public void setMaChucVu(Short maChucVu) {
		this.maChucVu = maChucVu;
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	/** @pdGenerated default getter */
	public Collection<NhanVien> getNhanVien() {
		if (nhanVien == null)
			nhanVien = new java.util.HashSet<NhanVien>();
		return nhanVien;
	}

	/**
	 * @pdGenerated default setter
	 * @param newNhanVien
	 */
	public void setNhanVien(java.util.Collection<NhanVien> newNhanVien) {
		removeAllNhanVien();
		for (Iterator<NhanVien> iter = newNhanVien.iterator(); iter.hasNext();)
			addNhanVien((NhanVien) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newNhanVien
	 */
	public void addNhanVien(NhanVien newNhanVien) {
		if (newNhanVien == null)
			return;
		if (this.nhanVien == null)
			this.nhanVien = new java.util.HashSet<NhanVien>();
		if (!this.nhanVien.contains(newNhanVien)) {
			this.nhanVien.add(newNhanVien);
			newNhanVien.setChucVu(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldNhanVien
	 */
	public void removeNhanVien(NhanVien oldNhanVien) {
		if (oldNhanVien == null)
			return;
		if (this.nhanVien != null)
			if (this.nhanVien.contains(oldNhanVien)) {
				this.nhanVien.remove(oldNhanVien);
				oldNhanVien.setChucVu((ChucVu) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllNhanVien() {
		if (nhanVien != null) {
			NhanVien oldNhanVien;
			for (Iterator<NhanVien> iter = getNhanVien().iterator(); iter.hasNext();) {
				oldNhanVien = (NhanVien) iter.next();
				iter.remove();
				oldNhanVien.setChucVu((ChucVu) null);
			}
		}
	}

}
