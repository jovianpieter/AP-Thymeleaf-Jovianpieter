package com.example.themeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HadirController {
    @Autowired
    private HadirRepository hadirrepository;

    @GetMapping("/")
    public String beranda(Model model) {
        Iterable<Hadir> semuaSiswa = hadirrepository.findAll();
        List<Hadir> listHadir = new ArrayList<>();
        List<Hadir> listTdkHadir = new ArrayList<>();

        for (Hadir status : semuaSiswa) {
            if (status.isHadir()) {
                listHadir.add(status);
            } else {
                listTdkHadir.add(status);
            }
        }

        model.addAttribute("listHadir", listHadir);
        model.addAttribute("listTdkHadir", listTdkHadir);
        return "kehadiran";
    }

    @GetMapping("/tambah")
    public String tambahSiswa(@RequestParam String nama) {
        Hadir tambah = new Hadir();
        tambah.setNama(nama);
        tambah.setHadir(false);
        hadirrepository.save(tambah);
        return "redirect:/";
    }

    @GetMapping("/ubah")
    public String ubahStatus(@RequestParam Integer id) {
        Hadir siswa = hadirrepository.findById(id).orElse(null);
        if (siswa != null) {
            siswa.setHadir(!siswa.isHadir());
            hadirrepository.save(siswa);
        }
        return "redirect:/";
    }
    @GetMapping("/update-nama")
    public String updateNama(@RequestParam Integer id, @RequestParam String namaBaru) {
        Hadir siswa = hadirrepository.findById(id).orElse(null);
        if (siswa != null) {
            siswa.setNama(namaBaru);
            hadirrepository.save(siswa);
        }
        return "redirect:/";
    }

    @GetMapping("/hapus")
    public String hapusdata(@RequestParam Integer id){
        if (hadirrepository.existsById(id)) {
            hadirrepository.deleteById(id);
        }
          return "redirect:/";
    }    
}
