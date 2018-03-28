package com.chothuesach.model;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.chothuesach.jsonview.ChucVuView;
import com.chothuesach.jsonview.NhanVienView;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class ChucVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ChucVuView.Overview.class, NhanVienView.Detailed.class})
	public Short maChucVu;

	@NotNull
	@JsonView({ChucVuView.Overview.class, NhanVienView.Detailed.class})
	public String tenChucVu;

	@OneToMany(mappedBy = "chucVu")
	@JsonView(ChucVuView.Detailed.class)
	public Collection<NhanVien> nhanVien;

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
