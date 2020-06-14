package com.alvinmd.androidrplbo.model;

public class WisataReligi {
    private String nama_wisata;
    private String deskripsi_wisata;
    private String thumb_wisata;
    private String navigasi_wisata;
    private String notelp_wisata;
    private String email_wisata;
    private String alamat_wisata;
    private String non_aktif;
    private String id;

    public WisataReligi() {

    }

    public WisataReligi(String nama_wisata, String deskripsi_wisata, String thumb_wisata, String navigasi_wisata, String notelp_wisata, String email_wisata, String alamat_wisata, String non_aktif, String id) {
        this.nama_wisata = nama_wisata;
        this.deskripsi_wisata = deskripsi_wisata;
        this.thumb_wisata = thumb_wisata;
        this.navigasi_wisata = navigasi_wisata;
        this.notelp_wisata = notelp_wisata;
        this.email_wisata = email_wisata;
        this.alamat_wisata = alamat_wisata;
        this.non_aktif = non_aktif;
        this.id = id;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getDeskripsi_wisata() {
        return deskripsi_wisata;
    }

    public void setDeskripsi_wisata(String deskripsi_wisata) {
        this.deskripsi_wisata = deskripsi_wisata;
    }

    public String getThumb_wisata() {
        return thumb_wisata;
    }

    public void setThumb_wisata(String thumb_wisata) {
        this.thumb_wisata = thumb_wisata;
    }

    public String getNavigasi_wisata() {
        return navigasi_wisata;
    }

    public void setNavigasi_wisata(String navigasi_wisata) {
        this.navigasi_wisata = navigasi_wisata;
    }

    public String getNotelp_wisata() {
        return notelp_wisata;
    }

    public void setNotelp_wisata(String notelp_wisata) {
        this.notelp_wisata = notelp_wisata;
    }

    public String getEmail_wisata() {
        return email_wisata;
    }

    public void setEmail_wisata(String email_wisata) {
        this.email_wisata = email_wisata;
    }

    public String getAlamat_wisata() {
        return alamat_wisata;
    }

    public void setAlamat_wisata(String alamat_wisata) {
        this.alamat_wisata = alamat_wisata;
    }

    public String getNon_aktif() {
        return non_aktif;
    }

    public void setNon_aktif(String non_aktif) {
        this.non_aktif = non_aktif;
    }

    public String getId() {
        return id;
    }
}
