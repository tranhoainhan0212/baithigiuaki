package com.redbull.webbanhang.Repository;

import com.redbull.webbanhang.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    Optional<NhanVien> findById(String maNV);
}