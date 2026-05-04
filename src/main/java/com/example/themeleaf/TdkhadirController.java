package com.example.themeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Tdkhadir")
public class TdkhadirController {
    @Autowired
    private TdkhadirRepository Tdkhadir;

    @GetMapping("/tambah")
    public @ResponseBody String tambahHadir(@RequestParam String nama){
        Tdkhadir tambah = new Tdkhadir();
        tambah.setNama(nama);
        Tdkhadir.save(tambah);
        return "telah ditambah" + nama;
    }
    
    @GetMapping("/hapus")
    public @ResponseBody String hapusdata(@RequestParam Integer id){
        if (Tdkhadir.existsById(id)) {
            Tdkhadir.deleteById(id);
            return "Data test10 dengan ID " + id + " berhasil dihapus!";
        } else {
            return "Gagal! Data dengan ID " + id + " tidak ditemukan.";
        }
    }    
}
