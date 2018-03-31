package com.chothuesach.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.chothuesach.helper.Slugify;
import com.chothuesach.jsonview.SachView;
import com.chothuesach.jsonview.TacGiaView;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class TacGia {
	
	@Id
	@GenericGenerator(name = "author_id", strategy = "com.chothuesach.generator.AuthorIdGenerator")
	@GeneratedValue(generator = "author_id")
	@JsonView({TacGiaView.Overview.class, SachView.Detailed.class})
	private String maTacGia;
	
	@NotNull
	@JsonView({TacGiaView.Overview.class, SachView.Detailed.class})
	private String tenTacGia;

	@NotNull
	@JsonView({TacGiaView.Overview.class, SachView.Detailed.class})
	private String slug;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "sachCuaTacGia")
	@JsonView(TacGiaView.Detailed.class)
	private Collection<Sach> sachCuaTacGia;

	public String getMaTacGia() {
		return maTacGia;
	}

	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	@PrePersist
	protected void onCreate() {
		slug = Slugify.toSlug(getTenTacGia());
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Collection<Sach> getSachCuaTacGia() {
		return sachCuaTacGia;
	}

	public void setSachCuaTacGia(Collection<Sach> sachCuaTacGia) {
		this.sachCuaTacGia = sachCuaTacGia;
	}

//	/** @pdGenerated default getter */
//	public Collection<Sach> getSachCuaTacGia() {
//		if (sachCuaTacGia == null)
//			sachCuaTacGia = new java.util.HashSet<Sach>();
//		return sachCuaTacGia;
//	}
//
//	/** @pdGenerated default iterator getter */
//	public Iterator<Sach> getIteratorSachCuaTacGia() {
//		if (sachCuaTacGia == null)
//			sachCuaTacGia = new java.util.HashSet<Sach>();
//		return sachCuaTacGia.iterator();
//	}
//
//	/**
//	 * @pdGenerated default setter
//	 * @param newSachCuaTacGia
//	 */
//	public void setSachCuaTacGia(Collection<Sach> newSachCuaTacGia) {
//		removeAllSachCuaTacGia();
//		for (Iterator<Sach> iter = newSachCuaTacGia.iterator(); iter.hasNext();)
//			addSachCuaTacGia((Sach) iter.next());
//	}
//
//	/**
//	 * @pdGenerated default add
//	 * @param newSach
//	 */
//	public void addSachCuaTacGia(Sach newSach) {
//		if (newSach == null)
//			return;
//		if (this.sachCuaTacGia == null)
//			this.sachCuaTacGia = new java.util.HashSet<Sach>();
//		if (!this.sachCuaTacGia.contains(newSach)) {
//			this.sachCuaTacGia.add(newSach);
//			newSach.addSachCuaTacGia(this);
//		}
//	}
//
//	/**
//	 * @pdGenerated default remove
//	 * @param oldSach
//	 */
//	public void removeSachCuaTacGia(Sach oldSach) {
//		if (oldSach == null)
//			return;
//		if (this.sachCuaTacGia != null)
//			if (this.sachCuaTacGia.contains(oldSach)) {
//				this.sachCuaTacGia.remove(oldSach);
//				oldSach.removeSachCuaTacGia(this);
//			}
//	}
//
//	/** @pdGenerated default removeAll */
//	public void removeAllSachCuaTacGia() {
//		if (sachCuaTacGia != null) {
//			Sach oldSach;
//			for (Iterator<Sach> iter = getIteratorSachCuaTacGia(); iter.hasNext();) {
//				oldSach = (Sach) iter.next();
//				iter.remove();
//				oldSach.removeSachCuaTacGia(this);
//			}
//		}
//	}

}
