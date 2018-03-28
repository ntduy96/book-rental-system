package com.chothuesach.model;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NhaPhanPhoi {

	@Id
	public long maNpp;
	
	public String tenNpp;

	@OneToMany(mappedBy="nhaPhanPhoi")
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	public Collection<PhieuNhapHang> phieuNhapHang;

	/** @pdGenerated default getter */
	public Collection<PhieuNhapHang> getPhieuNhapHang() {
		if (phieuNhapHang == null)
			phieuNhapHang = new java.util.HashSet<PhieuNhapHang>();
		return phieuNhapHang;
	}

	/** @pdGenerated default iterator getter */
	public Iterator<PhieuNhapHang> getIteratorPhieuNhapHang() {
		if (phieuNhapHang == null)
			phieuNhapHang = new java.util.HashSet<PhieuNhapHang>();
		return phieuNhapHang.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newPhieuNhapHang
	 */
	public void setPhieuNhapHang(Collection<PhieuNhapHang> newPhieuNhapHang) {
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
			newPhieuNhapHang.setNhaPhanPhoi(this);
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
				oldPhieuNhapHang.setNhaPhanPhoi((NhaPhanPhoi) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllPhieuNhapHang() {
		if (phieuNhapHang != null) {
			PhieuNhapHang oldPhieuNhapHang;
			for (Iterator<PhieuNhapHang> iter = getIteratorPhieuNhapHang(); iter.hasNext();) {
				oldPhieuNhapHang = (PhieuNhapHang) iter.next();
				iter.remove();
				oldPhieuNhapHang.setNhaPhanPhoi((NhaPhanPhoi) null);
			}
		}
	}

}
