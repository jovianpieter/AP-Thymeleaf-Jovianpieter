package com.example.themeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Hadir")
public class HadirController {
    @Autowired
    private HadirRepository hadir;

    @GetMapping("/tambah")
    public @ResponseBody String tambahHadir(@RequestParam String nama){
        Hadir tambah = new Hadir();
        tambah.setNama(nama);
        hadir.save(tambah);
        return "telah ditambah" + nama;
    }
    
    @GetMapping("/hapus")
    public @ResponseBody String hapusdata(@RequestParam Integer id){
        if (hadir.existsById(id)) {
            hadir.deleteById(id);
            return "Data test10 dengan ID " + id + " berhasil dihapus!";
        } else {
            return "Gagal! Data dengan ID " + id + " tidak ditemukan.";
        }
    }    
}
