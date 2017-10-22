package com.example.model;

import java.math.BigInteger;

public class KecamatanModel {
	private BigInteger id;
	private String kodeKecamatan;
	private BigInteger idKota;
	private String namaKecamatan;
	private KotaModel kota;
	
	public KecamatanModel(BigInteger id, String kodeKecamatan, BigInteger idKota, String namaKecamatan) {
		super();
		this.id=id;
		this.kodeKecamatan = kodeKecamatan;
		this.idKota = idKota;
		this.namaKecamatan = namaKecamatan;
	}
	public String getKodeKecamatan() {
		return kodeKecamatan;
	}
	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}
	public BigInteger getIdKota() {
		return idKota;
	}
	public void setIdKota(BigInteger idKota) {
		this.idKota = idKota;
	}
	public String getNamaKecamatan() {
		return namaKecamatan;
	}
	public void setNamaKecamatan(String namaKecamatan) {
		this.namaKecamatan = namaKecamatan;
	}
	public KotaModel getKota() {
		return kota;
	}
	public void setKota(KotaModel kota) {
		this.kota = kota;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
}
