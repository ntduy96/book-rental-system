package com.chothuesach.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class HoaDonDto {

    @NotEmpty
    @Valid
    private List<ChiTietHoaDonDto> chiTietHoaDonList;

    public List<ChiTietHoaDonDto> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDonDto> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }
}
