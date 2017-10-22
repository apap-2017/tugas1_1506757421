package com.example.model;

import java.math.BigInteger;
import java.util.List;

public class KeluargaModel {
	private String nomorKk;
	private String alamat;
	private String rt;
	private String rw;
	private BigInteger idKelurahan;
	private boolean isTidakBerlaku;
	private String kelurahan;
	private String kecamatan;
	private String kota;
	private List<PendudukModel> anggota;
	
	public KeluargaModel(String nomorKk, String alamat, String rt, String rw, BigInteger idKelurahan,
			boolean isTidakBerlaku, String kelurahan, String kecamatan, String kota) {
		super();
		this.nomorKk = nomorKk;
		this.alamat = alamat;
		this.rt = rt;
		this.rw = rw;
		this.idKelurahan = idKelurahan;
		this.isTidakBerlaku = isTidakBerlaku;
		this.kelurahan = kelurahan;
		this.kecamatan = kecamatan;
		this.kota = kota;
	}
	public String getNomorKk() {
		return nomorKk;
	}
	public void setNomorKk(String nomorKk) {
		this.nomorKk = nomorKk;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
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
	public BigInteger getIdKelurahan() {
		return idKelurahan;
	}
	public void setIdKelurahan(BigInteger idKelurahan) {
		this.idKelurahan = idKelurahan;
	}
	public boolean isTidakBerlaku() {
		return isTidakBerlaku;
	}
	public void setTidakBerlaku(boolean isTidakBerlaku) {
		this.isTidakBerlaku = isTidakBerlaku;
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
	public List<PendudukModel> getAnggota() {
		return anggota;
	}
	public void setAnggota(List<PendudukModel> anggota) {
		this.anggota = anggota;
	}
	
}
