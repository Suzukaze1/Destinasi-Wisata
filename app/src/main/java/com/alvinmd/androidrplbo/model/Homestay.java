package com.alvinmd.androidrplbo.model;

public class Homestay {

    private String nama_homestay;
    private String deskripsi_homestay;
    private String thumb_homestay;
    private String navigasi_homestay;
    private String notelp_homestay;
    private String email_homestay;
    private String alamat_homestay;
    private String non_aktif;
    private String id;

    public Homestay() {

    }

    public Homestay(String nama_homestay, String deskripsi_homestay, String thumb_homestay, String navigasi_homestay, String notelp_homestay, String email_homestay, String alamat_homestay, String non_aktif, String id) {
        this.nama_homestay = nama_homestay;
        this.deskripsi_homestay = deskripsi_homestay;
        this.thumb_homestay = thumb_homestay;
        this.navigasi_homestay = navigasi_homestay;
        this.notelp_homestay = notelp_homestay;
        this.email_homestay = email_homestay;
        this.alamat_homestay = alamat_homestay;
        this.non_aktif = non_aktif;
        this.id = id;
    }

    public String getNama_homestay() {
        return nama_homestay;
    }

    public void setNama_homestay(String nama_homestay) {
        this.nama_homestay = nama_homestay;
    }

    public String getDeskripsi_homestay() {
        return deskripsi_homestay;
    }

    public void setDeskripsi_homestay(String deskripsi_homestay) {
        this.deskripsi_homestay = deskripsi_homestay;
    }

    public String getThumb_homestay() {
        return thumb_homestay;
    }

    public void setThumb_homestay(String thumb_homestay) {
        this.thumb_homestay = thumb_homestay;
    }

    public String getNavigasi_homestay() {
        return navigasi_homestay;
    }

    public void setNavigasi_homestay(String navigasi_homestay) {
        this.navigasi_homestay = navigasi_homestay;
    }

    public String getNotelp_homestay() {
        return notelp_homestay;
    }

    public void setNotelp_homestay(String notelp_homestay) {
        this.notelp_homestay = notelp_homestay;
    }

    public String getEmail_homestay() {
        return email_homestay;
    }

    public void setEmail_homestay(String email_homestay) {
        this.email_homestay = email_homestay;
    }

    public String getAlamat_homestay() {
        return alamat_homestay;
    }

    public void setAlamat_homestay(String alamat_homestay) {
        this.alamat_homestay = alamat_homestay;
    }

    public String getNon_aktif() {
        return non_aktif;
    }

    public String getId() {
        return id;
    }
}
