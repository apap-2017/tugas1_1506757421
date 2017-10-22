package com.example.model;

import java.math.BigInteger;

public class KotaModel {
	private BigInteger id;
	private String kodeKota;
	private String namaKota;
	
	public KotaModel(BigInteger id, String kodeKota, String namaKota) {
		this.id = id;
		this.kodeKota = kodeKota;
		this.namaKota = namaKota;
	}

	public String getKodeKota() {
		return kodeKota;
	}

	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}

	public String getNamaKota() {
		return namaKota;
	}

	public void setNamaKota(String namaKota) {
		this.namaKota = namaKota;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
}
