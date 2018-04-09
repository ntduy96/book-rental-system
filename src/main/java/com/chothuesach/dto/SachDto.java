package com.chothuesach.dto;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Set;

public class SachDto {

	@NotBlank(message = "Không được bỏ trống")
	private String tenSach;

	@URL
	private String anhBia;

	@NotNull(message = "Không được bỏ trống")
	@PositiveOrZero
	private Long soLuong;

	@NotNull(message = "Không được bỏ trống")
	@PositiveOrZero
	private Integer soTrang;

	@DateTimeFormat(iso = ISO.DATE)
	private Date ngayXuatBan;
	
	@NotEmpty(message = "Không được bỏ trống")
	private Set<String> theLoai;
	
	@NotEmpty(message = "Không được bỏ trống")
	private Set<String> tacGia;

	@NotNull(message = "Không được bỏ trống")
	@PositiveOrZero
	private Double donGiaBan;

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

	public Long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}

	public Integer getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(Integer soTrang) {
		this.soTrang = soTrang;
	}

	public Date getNgayXuatBan() {
		return ngayXuatBan;
	}

	public void setNgayXuatBan(Date ngayXuatBan) {
		this.ngayXuatBan = ngayXuatBan;
	}

	public Double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(Double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public Set<String> getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(Set<String> theLoai) {
		this.theLoai = theLoai;
	}

	public Set<String> getTacGia() {
		return tacGia;
	}

	public void setTacGia(Set<String> tacGia) {
		this.tacGia = tacGia;
	}

}
