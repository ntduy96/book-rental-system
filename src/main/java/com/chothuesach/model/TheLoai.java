package com.chothuesach.model;

import com.chothuesach.helper.Slugify;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.jsonview.TheLoaiView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class TheLoai implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({SachView.Detailed.class, TheLoaiView.Overview.class})
	private Long maTheLoai;
	
	@NotNull
	@JsonView({SachView.Detailed.class, TheLoaiView.Overview.class})
	private String tenTheLoai;

	@NotNull
	@JsonView({SachView.Detailed.class, TheLoaiView.Overview.class})
	private String slug;

	@ManyToMany(fetch=FetchType.LAZY,
		mappedBy="sachThuocTheLoai"
	)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonView(TheLoaiView.Detailed.class)
	@JsonBackReference
	private Collection<Sach> sachThuocTheLoai;

	@PrePersist
	protected void onCreate() {
		slug = Slugify.toSlug(getTenTheLoai());
	}

	public Long getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(Long maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Collection<Sach> getSachThuocTheLoai() {
		return sachThuocTheLoai;
	}

	public void setSachThuocTheLoai(Collection<Sach> sachThuocTheLoai) {
		this.sachThuocTheLoai = sachThuocTheLoai;
	}

	/** @pdGenerated default getter *//*
	public Collection<Sach> getSachThuocTheLoai() {
		if (sachThuocTheLoai == null)
			sachThuocTheLoai = new java.util.HashSet<Sach>();
		return sachThuocTheLoai;
	}

	*//** @pdGenerated default iterator getter *//*
	public Iterator<Sach> getIteratorSachThuocTheLoai() {
		if (sachThuocTheLoai == null)
			sachThuocTheLoai = new java.util.HashSet<Sach>();
		return sachThuocTheLoai.iterator();
	}

	*//**
	 * @pdGenerated default setter
	 * @param newSachThuocTheLoai
	 *//*
	public void setSachThuocTheLoai(Collection<Sach> newSachThuocTheLoai) {
		removeAllSachThuocTheLoai();
		for (Iterator<Sach> iter = newSachThuocTheLoai.iterator(); iter.hasNext();)
			addSachThuocTheLoai((Sach) iter.next());
	}

	*//**
	 * @pdGenerated default add
	 * @param newSach
	 *//*
	public void addSachThuocTheLoai(Sach newSach) {
		if (newSach == null)
			return;
		if (this.sachThuocTheLoai == null)
			this.sachThuocTheLoai = new java.util.HashSet<Sach>();
		if (!this.sachThuocTheLoai.contains(newSach)) {
			this.sachThuocTheLoai.add(newSach);
			newSach.addSachThuocTheLoai(this);
		}
	}

	*//**
	 * @pdGenerated default remove
	 * @param oldSach
	 *//*
	public void removeSachThuocTheLoai(Sach oldSach) {
		if (oldSach == null)
			return;
		if (this.sachThuocTheLoai != null)
			if (this.sachThuocTheLoai.contains(oldSach)) {
				this.sachThuocTheLoai.remove(oldSach);
				oldSach.removeSachThuocTheLoai(this);
			}
	}

	*//** @pdGenerated default removeAll *//*
	public void removeAllSachThuocTheLoai() {
		if (sachThuocTheLoai != null) {
			Sach oldSach;
			for (Iterator<Sach> iter = getIteratorSachThuocTheLoai(); iter.hasNext();) {
				oldSach = (Sach) iter.next();
				iter.remove();
				oldSach.removeSachThuocTheLoai(this);
			}
		}
	}
*/
}
