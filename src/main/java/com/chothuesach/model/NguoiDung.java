package com.chothuesach.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.chothuesach.jsonview.ChucVuView;
import com.chothuesach.jsonview.HoaDonView;
import com.chothuesach.jsonview.NguoiDungView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class NguoiDung implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "user_id", strategy = "com.chothuesach.generator.UserIdGenerator")
	@GeneratedValue(generator = "user_id")
	@JsonView({ ChucVuView.Detailed.class, NguoiDungView.Overview.class, HoaDonView.Detailed.class })
	private String maNguoiDung;

	@NotNull
	@JsonView({ ChucVuView.Detailed.class, NguoiDungView.Overview.class })
	private String tenNguoiDung;

	@NotNull
	@JsonView(NguoiDungView.Detailed.class)
	private String hoTenNguoiDung;

	@NotNull
	@JsonView(NguoiDungView.Detailed.class)
	private String diaChiNguoiDung;

	@NotNull
	@JsonView(NguoiDungView.Detailed.class)
	private String soCmnd;

	@JsonView(NguoiDungView.Detailed.class)
	private String anhDaiDien;

	@NotNull
	@JsonView(NguoiDungView.Detailed.class)
	private String email;

	@NotNull
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy = "khachHang")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private Collection<HoaDon> hoaDon; // Hoa don khach hang da dat hang

	@NotNull
	private boolean enabled = true;

	// @OneToMany(mappedBy = "tenNguoiDung", cascade = CascadeType.ALL, fetch =
	// FetchType.EAGER)
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "NGUOI_DUNG_ROLE", joinColumns = {
			@JoinColumn(name = "TEN_NGUOI_DUNG", referencedColumnName = "tenNguoiDung") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId") })
	private Collection<Role> roles;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getTenNguoiDung() {
		return tenNguoiDung;
	}

	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}

	public String getHoTenNguoiDung() {
		return hoTenNguoiDung;
	}

	public void setHoTenNguoiDung(String hoTenNguoiDung) {
		this.hoTenNguoiDung = hoTenNguoiDung;
	}

	public String getDiaChiNguoiDung() {
		return diaChiNguoiDung;
	}

	public void setDiaChiNguoiDung(String diaChiNguoiDung) {
		this.diaChiNguoiDung = diaChiNguoiDung;
	}

	public String getSoCmnd() {
		return soCmnd;
	}

	public void setSoCmnd(String soCmnd) {
		this.soCmnd = soCmnd;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** @pdGenerated default getter */
	public Collection<HoaDon> getHoaDon() {
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
			newHoaDon.setKhachHang(this);
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
				oldHoaDon.setKhachHang((NguoiDung) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllHoaDon() {
		if (hoaDon != null) {
			HoaDon oldHoaDon;
			for (Iterator<HoaDon> iter = getHoaDon().iterator(); iter.hasNext();) {
				oldHoaDon = (HoaDon) iter.next();
				iter.remove();
				oldHoaDon.setKhachHang((NguoiDung) null);
			}
		}
	}

}
