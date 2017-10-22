package com.example.service;

import java.math.BigInteger;

import com.example.model.KeluargaModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga (String nomorKk);
	
	void addKeluarga (KeluargaModel keluarga);
	
	int searchSequenceByNkk(String subNkk);
	
	void ubahKeluarga(KeluargaModel keluarga, String oldNkk);
	
	KeluargaModel searchKeluargaByID(BigInteger id);
	
	void deactivateKeluarga(String nkk);
	
	int countAnggotaHidup(String nkk);
}
