package com.chothuesach.model;

import com.chothuesach.jsonview.NguoiDungView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@NotNull
	@JsonView(NguoiDungView.Detailed.class)
	private String roleName;

	@NotNull
//	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "NGUOI_DUNG_ROLE", joinColumns = {
//			@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId") }, inverseJoinColumns = {
//			@JoinColumn(name = "TEN_NGUOI_DUNG", referencedColumnName = "tenNguoiDung") })
	@ManyToMany(mappedBy = "roles")
	private Collection<NguoiDung> nguoiDungs;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<NguoiDung> getNguoiDungs() {
		return nguoiDungs;
	}

	public void setNguoiDungs(Collection<NguoiDung> nguoiDungs) {
		this.nguoiDungs = nguoiDungs;
	}

}
