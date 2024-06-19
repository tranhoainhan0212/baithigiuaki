package com.redbull.webbanhang.Service;


import com.redbull.webbanhang.Repository.NhanVienRepository;
import com.redbull.webbanhang.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Transactional(readOnly = true)
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    @Transactional
    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @Transactional(readOnly = true)
    public Optional<NhanVien> getNhanVienById(String maNV) {
        return nhanVienRepository.findById(maNV);
    }

    @Transactional
    public NhanVien updateNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @Transactional
    public void deleteNhanVienById(String maNV) {
        nhanVienRepository.deleteById(maNV);
    }

    @Transactional
    public void deleteNhanVien(NhanVien nhanVien) {
        nhanVienRepository.delete(nhanVien);
    }

    @Transactional
    public void deleteAllNhanVien() {
        nhanVienRepository.deleteAll();
    }
}