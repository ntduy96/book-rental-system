package com.chothuesach.model;

import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.chothuesach.jsonview.SachView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class ThoiDiem {

	@Id
	@JsonView(SachView.Detailed.class)
	public java.util.Date thoiGian;

	@OneToMany(mappedBy="thoiDiem")
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	public java.util.Collection<DonGiaBan> donGiaBan;

	/** @pdGenerated default getter */
	public java.util.Collection<DonGiaBan> getDonGiaBan() {
		if (donGiaBan == null)
			donGiaBan = new java.util.HashSet<DonGiaBan>();
		return donGiaBan;
	}

	/** @pdGenerated default iterator getter */
	public Iterator<DonGiaBan> getIteratorDonGiaBan() {
		if (donGiaBan == null)
			donGiaBan = new java.util.HashSet<DonGiaBan>();
		return donGiaBan.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newDonGiaBan
	 */
	public void setDonGiaBan(java.util.Collection<DonGiaBan> newDonGiaBan) {
		removeAllDonGiaBan();
		for (Iterator<DonGiaBan> iter = newDonGiaBan.iterator(); iter.hasNext();)
			addDonGiaBan((DonGiaBan) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newDonGiaBan
	 */
	public void addDonGiaBan(DonGiaBan newDonGiaBan) {
		if (newDonGiaBan == null)
			return;
		if (this.donGiaBan == null)
			this.donGiaBan = new java.util.HashSet<DonGiaBan>();
		if (!this.donGiaBan.contains(newDonGiaBan)) {
			this.donGiaBan.add(newDonGiaBan);
			newDonGiaBan.setThoiDiem(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldDonGiaBan
	 */
	public void removeDonGiaBan(DonGiaBan oldDonGiaBan) {
		if (oldDonGiaBan == null)
			return;
		if (this.donGiaBan != null)
			if (this.donGiaBan.contains(oldDonGiaBan)) {
				this.donGiaBan.remove(oldDonGiaBan);
				oldDonGiaBan.setThoiDiem((ThoiDiem) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllDonGiaBan() {
		if (donGiaBan != null) {
			DonGiaBan oldDonGiaBan;
			for (Iterator<DonGiaBan> iter = getIteratorDonGiaBan(); iter.hasNext();) {
				oldDonGiaBan = (DonGiaBan) iter.next();
				iter.remove();
				oldDonGiaBan.setThoiDiem((ThoiDiem) null);
			}
		}
	}

}
