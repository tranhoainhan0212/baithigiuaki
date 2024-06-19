package com.redbull.webbanhang.controller;

import com.redbull.webbanhang.Service.NhanVienService;
import com.redbull.webbanhang.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String listNhanVien(Model model) {
        List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanVienList", nhanVienList);
        return "nhanvien/list";
    }

    @GetMapping("/add")
    public String addNhanVienForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/add";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
                              BindingResult result,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanVien", nhanVien);
            return "nhanvien/add";
        }

        if (!imageFile.isEmpty()) {
            try {
                // Tạo thư mục nếu chưa tồn tại
                Path uploadDir = Paths.get(uploadPath);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Lưu tệp ảnh
                String fileName = imageFile.getOriginalFilename();
                Path path = uploadDir.resolve(fileName);
                Files.write(path, imageFile.getBytes());
                nhanVien.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("nhanVien", nhanVien);
                return "nhanvien/add";
            }
        }

        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }
    @GetMapping("/edit/{maNV}")
    public String showEditForm(@PathVariable String maNV, Model model) {
        Optional<NhanVien> nhanVienOpt = nhanVienService.getNhanVienById(maNV);
        if (nhanVienOpt.isPresent()) {
            model.addAttribute("nhanVien", nhanVienOpt.get());
            return "nhanvien/update";
        } else {
            return "redirect:/nhanvien";
        }
    }

    @PostMapping("/update/{maNV}")
    public String updateNhanVien(@PathVariable String maNV, @Valid @ModelAttribute("nhanVien") NhanVien nhanVien,
                                 BindingResult result,
                                 @RequestParam("imageFile") MultipartFile imageFile,
                                 Model model) {
        if (result.hasErrors()) {
            nhanVien.setMaNV(maNV);
            return "nhanvien/update";
        }

        if (!imageFile.isEmpty()) {
            try {
                // Xóa ảnh cũ trước khi lưu ảnh mới
                Optional<NhanVien> existingNhanVienOpt = nhanVienService.getNhanVienById(maNV);
                if (existingNhanVienOpt.isPresent()) {
                    NhanVien existingNhanVien = existingNhanVienOpt.get();
                    if (existingNhanVien.getImage() != null && !existingNhanVien.getImage().isEmpty()) {
                        Path oldImagePath = Paths.get(uploadPath).resolve(existingNhanVien.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }
                }

                // Lưu ảnh mới
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath).resolve(fileName);
                Files.write(path, imageFile.getBytes());
                nhanVien.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                nhanVien.setMaNV(maNV);
                model.addAttribute("nhanVien", nhanVien);
                return "nhanvien/update";
            }
        }

        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/delete/{maNV}")
    public String deleteNhanVien(@PathVariable String maNV) {
        Optional<NhanVien> nhanVienOpt = nhanVienService.getNhanVienById(maNV);
        if (nhanVienOpt.isPresent()) {
            NhanVien nhanVien = nhanVienOpt.get();
            if (nhanVien.getImage() != null && !nhanVien.getImage().isEmpty()) {
                try {
                    Path imagePath = Paths.get(uploadPath).resolve(nhanVien.getImage());
                    Files.deleteIfExists(imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            nhanVienService.deleteNhanVienById(maNV);
        }
        return "redirect:/nhanvien";
    }
}