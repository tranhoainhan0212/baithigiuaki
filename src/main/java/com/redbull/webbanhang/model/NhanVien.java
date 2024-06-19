package com.redbull.webbanhang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class NhanVien {

    @Id
    private String maNV;

    @NotBlank(message = "Tên nhân viên không được để trống")
    private String tenNV;

    @NotBlank(message = "Phái không được để trống")
    private String phai;

    @NotBlank(message = "Nơi sinh không được để trống")
    private String noiSinh;

    @NotBlank(message = "Mã phòng không được để trống")
    private String maPhong;

    @NotNull(message = "Lương không được để trống")
    private Double luong;

    private String image;

    // Getters and Setters

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Double getLuong() {
        return luong;
    }

    public void setLuong(Double luong) {
        this.luong = luong;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
