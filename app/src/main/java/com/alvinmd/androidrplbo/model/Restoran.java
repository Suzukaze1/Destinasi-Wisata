package com.alvinmd.androidrplbo.model;

public class Restoran {
    private String nama_restoran;
    private String deskripsi_restoran;
    private String thumb_restoran;
    private String navigasi_restoran;
    private String notelp_restoran;
    private String email_restoran;
    private String alamat_restoran;
    private String non_aktif;
    private String id;

    public Restoran() {

    }

    public Restoran(String nama_restoran, String deskripsi_restoran, String thumb_restoran, String navigasi_restoran, String notelp_restoran, String email_restoran, String alamat_restoran, String non_aktif, String id) {
        this.nama_restoran = nama_restoran;
        this.deskripsi_restoran = deskripsi_restoran;
        this.thumb_restoran = thumb_restoran;
        this.navigasi_restoran = navigasi_restoran;
        this.notelp_restoran = notelp_restoran;
        this.email_restoran = email_restoran;
        this.alamat_restoran = alamat_restoran;
        this.non_aktif = non_aktif;
        this.id = id;
    }

    public String getNama_restoran() {
        return nama_restoran;
    }

    public void setNama_restoran(String nama_restoran) {
        this.nama_restoran = nama_restoran;
    }

    public String getDeskripsi_restoran() {
        return deskripsi_restoran;
    }

    public void setDeskripsi_restoran(String deskripsi_restoran) {
        this.deskripsi_restoran = deskripsi_restoran;
    }

    public String getThumb_restoran() {
        return thumb_restoran;
    }

    public void setThumb_restoran(String thumb_restoran) {
        this.thumb_restoran = thumb_restoran;
    }

    public String getNavigasi_restoran() {
        return navigasi_restoran;
    }

    public void setNavigasi_restoran(String navigasi_restoran) {
        this.navigasi_restoran = navigasi_restoran;
    }

    public String getNotelp_restoran() {
        return notelp_restoran;
    }

    public void setNotelp_restoran(String notelp_restoran) {
        this.notelp_restoran = notelp_restoran;
    }

    public String getEmail_restoran() {
        return email_restoran;
    }

    public void setEmail_restoran(String email_restoran) {
        this.email_restoran = email_restoran;
    }

    public String getAlamat_restoran() {
        return alamat_restoran;
    }

    public void setAlamat_restoran(String alamat_restoran) {
        this.alamat_restoran = alamat_restoran;
    }

    public String getNon_aktif() {
        return non_aktif;
    }

    public String getId() {
        return id;
    }
}
