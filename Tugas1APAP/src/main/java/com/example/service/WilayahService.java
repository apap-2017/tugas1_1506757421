package com.example.service;

import java.math.BigInteger;
import java.util.List;

import com.example.model.KelurahanModel;
import com.example.model.KecamatanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

public interface WilayahService {
	BigInteger getKelurahanIDByName(String name);
	
	BigInteger getKecamatanKodeByName(String name);
	
	BigInteger getKotaKodeByName(String name);
	
	BigInteger getKecamatanKodeByIDKeluarga(BigInteger idKeluarga);
	
	BigInteger getKecamatanKodeByKelurahan(String kelurahan);
	
	String getKelurahanNameByID(BigInteger kelurahan);
	
	List<PendudukModel> selectPendudukInWilayah(BigInteger idKel, BigInteger idKec, BigInteger idKota);
	
	List<KotaModel> selectAllKota();
	
	List<KecamatanModel> selectKecByKota(BigInteger idKota);
	
	String getKotaNameByID(BigInteger idKota);
	
	List<KelurahanModel> selectKelurahanByKecID(BigInteger idKec);

	String getKecNameByID(BigInteger idKec);

	KotaModel selectKota(BigInteger idKota);

	KecamatanModel selectKecamatan(BigInteger idKec);

	KelurahanModel selectKelurahan(BigInteger idKel);
}
