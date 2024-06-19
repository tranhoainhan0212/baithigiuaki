package com.redbull.webbanhang.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PhongBan {
    @Id
    private String maPhong;
    private String tenPhong;

    // Getters and setters
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
}
