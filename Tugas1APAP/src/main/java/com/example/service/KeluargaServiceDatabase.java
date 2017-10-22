package com.example.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectKeluarga(String nomorKk) {
		log.info  ("select keluarga with nkk {}", nomorKk);
		return keluargaMapper.selectKeluarga(nomorKk);
	}

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		log.info("add keluarga with nkk {} ", keluarga.getNomorKk());
		keluargaMapper.addKeluarga(keluarga);
	}

	@Override
	public int searchSequenceByNkk(String subNkk) {
		log.info("search sequence by sub nkk {}", subNkk);
		return keluargaMapper.searchSequenceByNkk(subNkk);
	}

	@Override
	public void ubahKeluarga(KeluargaModel keluarga, String oldNkk) {
		log.info("ubah keluarga dengan nkk {}", oldNkk);
		keluargaMapper.ubahKeluarga(keluarga, oldNkk);
	}

	@Override
	public KeluargaModel searchKeluargaByID(BigInteger id) {
		log.info("search keluarga by ID {}", id);
		return keluargaMapper.searchKeluargaByID(id);
	}

	@Override
	public void deactivateKeluarga(String nkk) {
		log.info("deactivate keluarga with nkk {}", nkk);
		keluargaMapper.deactivateKeluarga(nkk);
	}

	@Override
	public int countAnggotaHidup(String nkk) {
		log.info("count anggota hidup of {}", nkk);
		int anggota = keluargaMapper.countAnggotaHidup(nkk);
		log.info("anggota hidup = ", anggota);
		return anggota;
	}


}
