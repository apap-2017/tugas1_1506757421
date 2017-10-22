package com.example.model;

import java.math.BigInteger;
import java.sql.Date;

public class PendudukModel {
	private String nik;
	private String nama;
	private String tempatLahir;
	private Date tanggalLahir;
	private int jenisKelamin;
	private boolean isWni;
	private BigInteger idKeluarga;
	private String agama;
	private String pekerjaan;
	private String statusKawin;
	private String statusDalamKeluarga;
	private String golDarah;
	private boolean isWafat;
	private String rt;
	private String rw;
	private String alamat;
	private String kelurahan;
	private String kecamatan;
	private String kota;
	
	public PendudukModel(String nik, String nama, String tempatLahir, Date tanggalLahir, int jenisKelamin, boolean isWni, BigInteger idKeluarga,
			String agama, String pekerjaan, String statusKawin, String statusDalamKeluarga, String golDarah,
			boolean isWafat, String kota, String kecamatan, String kelurahan, String alamat, String rt, String rw) {
		this.nik = nik;
		this.nama = nama;
		this.tempatLahir = tempatLahir;
		this.tanggalLahir = tanggalLahir;
		this.jenisKelamin = jenisKelamin;
		this.isWni = isWni;
		this.idKeluarga = idKeluarga;
		this.agama = agama;
		this.pekerjaan = pekerjaan;
		this.statusKawin = statusKawin;
		this.statusDalamKeluarga = statusDalamKeluarga;
		this.golDarah = golDarah;
		this.isWafat = isWafat;
		this.rt = rt;
		this.rw = rw;
		this.alamat = alamat;
		this.kelurahan = kelurahan;
		this.kecamatan = kecamatan;
		this.kota = kota;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTempatLahir() {
		return tempatLahir;
	}
	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}
	public Date getTanggalLahir() {
		return tanggalLahir;
	}
	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	public int getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(int jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public boolean isWni() {
		return isWni;
	}
	public void setWni(boolean isWni) {
		this.isWni = isWni;
	}
	public BigInteger getIdKeluarga() {
		return idKeluarga;
	}
	public void setIdKeluarga(BigInteger idKeluarga) {
		this.idKeluarga = idKeluarga;
	}
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	public String getPekerjaan() {
		return pekerjaan;
	}
	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}
	public String getStatusKawin() {
		return statusKawin;
	}
	public void setStatusKawin(String statusKawin) {
		this.statusKawin = statusKawin;
	}
	public String getStatusDalamKeluarga() {
		return statusDalamKeluarga;
	}
	public void setStatusDalamKeluarga(String statusDalamKeluarga) {
		this.statusDalamKeluarga = statusDalamKeluarga;
	}
	public String getGolDarah() {
		return golDarah;
	}
	public void setGolDarah(String golDarah) {
		this.golDarah = golDarah;
	}
	public boolean isWafat() {
		return isWafat;
	}
	public void setWafat(boolean isWafat) {
		this.isWafat = isWafat;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getRw() {
		return rw;
	}
	public void setRw(String rw) {
		this.rw = rw;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}
	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	
}
