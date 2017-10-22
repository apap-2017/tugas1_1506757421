package com.example.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.dao.KelurahanMapper;
import com.example.dao.KotaMapper;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WilayahServiceDatabase implements WilayahService {
	@Autowired
	KelurahanMapper kelurahanMapper;
	
	@Autowired
	KecamatanMapper kecamatanMapper;
	
	@Autowired
	KotaMapper kotaMapper;

	@Override
	public BigInteger getKelurahanIDByName(String name) {
		log.info("select id by name{}", name);
		return kelurahanMapper.getIDByName(name);
	}



	@Override
	public BigInteger getKecamatanKodeByName(String name) {
		log.info("select id by name{}", name);
		return kecamatanMapper.getKodeByName(name);
	}



	@Override
	public BigInteger getKotaKodeByName(String name) {
		log.info("select id by name{}", name);
		return kotaMapper.getKodeByName(name);
	}



	@Override
	public BigInteger getKecamatanKodeByIDKeluarga(BigInteger idKeluarga) {
		log.info("select kode kecamatan by id keluarga");
		return kecamatanMapper.getKecamatanKodeByIDKeluarga(idKeluarga);
	}



	@Override
	public BigInteger getKecamatanKodeByKelurahan(String kelurahan) {
		log.info("select kode kecamatan by nama kelurahan {}", kelurahan);
		return kecamatanMapper.getKecamatanKodeByKelurahan(kelurahan);
	}



	@Override
	public String getKelurahanNameByID(BigInteger kelurahan) {
		log.info("select nama kelurahan by id {}", kelurahan);
		return kelurahanMapper.getKelurahanNameByID(kelurahan);
	}



	@Override
	public List<PendudukModel> selectPendudukInWilayah(BigInteger idKel, BigInteger idKec, BigInteger idKota) {
		log.info("select penduduk di kelurahan {}", idKel);
		return kelurahanMapper.selectPendudukInWilayah(idKel, idKec, idKota);
	}



	@Override
	public List<KotaModel> selectAllKota() {
		log.info("select all kota");
		return kotaMapper.selectAllKota();
	}



	@Override
	public List<KecamatanModel> selectKecByKota(BigInteger idKota) {
		log.info("select all kecamatan in kota {}", idKota);
		return kecamatanMapper.selectKecByKota(idKota);
	}



	@Override
	public String getKotaNameByID(BigInteger idKota) {
		log.info("select nama kota dengan id {}", idKota);
		return kotaMapper.getKotaNameByID(idKota);
	}



	@Override
	public List<KelurahanModel> selectKelurahanByKecID(BigInteger idKec) {
		log.info("select nama kelurahan in kecamatan {}", idKec);
		return kelurahanMapper.selectKelurahanByKecID(idKec);
	}



	@Override
	public String getKecNameByID(BigInteger idKec) {
		log.info("select nama kecamatan dengan id {}", idKec);
		return kecamatanMapper.getKecNameByID(idKec);
	}



	@Override
	public KotaModel selectKota(BigInteger idKota) {
		log.info("select kota with id {}", idKota);
		return kotaMapper.selectKota(idKota);
	}



	@Override
	public KecamatanModel selectKecamatan(BigInteger idKec) {
		log.info("select kecamatan with id {}", idKec);
		return kecamatanMapper.selectKecamatan(idKec);
	}



	@Override
	public KelurahanModel selectKelurahan(BigInteger idKel) {
		log.info("select kelurahan with id {}", idKel);
		return kelurahanMapper.selectKelurahan(idKel);
	}

}
