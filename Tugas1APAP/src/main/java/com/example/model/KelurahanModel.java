package com.example.model;

import java.math.BigInteger;

public class KelurahanModel {
	private BigInteger id;
	private String kodeKelurahan;
	private BigInteger idKecamatan;
	private String namaKecamatan;
	private String kodePos;
	private String namaKelurahan;
	private KecamatanModel kecamatan;
	
	public KelurahanModel(BigInteger id, String kodeKelurahan, BigInteger idKecamatan, String namaKecamatan, String kodePos, String namaKelurahan) {
		super();
		this.id=id;
		this.kodeKelurahan = kodeKelurahan;
		this.idKecamatan = idKecamatan;
		this.namaKecamatan = namaKecamatan;
		this.kodePos = kodePos;
		this.namaKelurahan = namaKelurahan;
	}
	public String getKodeKelurahan() {
		return kodeKelurahan;
	}
	public void setKodeKelurahan(String kodeKelurahan) {
		this.kodeKelurahan = kodeKelurahan;
	}
	public BigInteger getIdKecamatan() {
		return idKecamatan;
	}
	public void setIdKecamatan(BigInteger idKecamatan) {
		this.idKecamatan = idKecamatan;
	}
	public String getNamaKecamatan() {
		return namaKecamatan;
	}
	public void setNamaKecamatan(String namaKecamatan) {
		this.namaKecamatan = namaKecamatan;
	}
	public String getKodePos() {
		return kodePos;
	}
	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}
	public KecamatanModel getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(KecamatanModel kecamatan) {
		this.kecamatan = kecamatan;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getNamaKelurahan() {
		return namaKelurahan;
	}
	public void setNamaKelurahan(String namaKelurahan) {
		this.namaKelurahan = namaKelurahan;
	}
	
}
