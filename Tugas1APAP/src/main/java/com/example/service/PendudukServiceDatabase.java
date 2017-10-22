package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	
	@Autowired
	private PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info  ("select penduduk with nik {}", nik);
		PendudukModel returns = pendudukMapper.selectPenduduk(nik); 
		return returns;
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		log.info("add penduduk with nik {}", penduduk.getNik());
		pendudukMapper.addPenduduk(penduduk);
	}

	@Override
	public int searchSequenceByNik(String subNik) {
		log.info("Search sequence by nik {}", subNik);
		return pendudukMapper.searchSequenceByNik(subNik);
	}

	@Override
	public void ubahPenduduk(PendudukModel penduduk, String oldNik) {
		log.info("Ubah penduduk dengan nik {}", oldNik);
		pendudukMapper.ubahPenduduk(penduduk, oldNik);
	}

	@Override
	public void deactivatePenduduk(String nik) {
		log.info("deactivate penduduk with nik {}", nik);
		pendudukMapper.deactivatePenduduk(nik);
	}
	
	

}
