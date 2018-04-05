package com.chothuesach.dto;

import javax.validation.constraints.NotNull;

public class NhanVienDto extends NguoiDungDto {

    @NotNull
    private Short maChucVu;

    public Short getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(Short maChucVu) {
        this.maChucVu = maChucVu;
    }
}
