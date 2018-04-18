package com.chothuesach.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class NguoiDungUpdateDto {

    @Length(min = 6, message = "Tên người dùng quá ngắn (từ 6 ký tự trở lên)")
    private String tenNguoiDung;

    @Length(min = 6, message = "Họ tên quá ngắn không hợp lệ")
    private String hoTenNguoiDung;

    private String diaChiNguoiDung;

    @Digits(fraction = 0, integer = 12, message = "So CMND chi bao gom cac chu so tu 0 den 9")
    private String soCmnd;

    @Length(min = 3, message = "Email quá ngắn")
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$", message = "Email duoc nhap khong dung dinh dang")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_{}()])(?=\\S+$).{8,}$", message = "Mật khẩu của bạn có vẻ chưa đủ an toàn\nCần chứa ít nhất 1 chữ số, 1 ký tự thường, 1 ký tự in hoa, 1 ký tự đặc biệt và độ dài từ 8 ký tự trở lên")
    private String password;

    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
