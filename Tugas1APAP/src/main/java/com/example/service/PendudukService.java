package com.example.service;

import com.example.model.PendudukModel;

public interface PendudukService {
	PendudukModel  selectPenduduk(String nik);
	
	void addPenduduk(PendudukModel penduduk);
	
	int searchSequenceByNik(String subNik);
	
	void ubahPenduduk(PendudukModel penduduk, String oldNik);
	
	void deactivatePenduduk(String nik);
}
