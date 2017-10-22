package com.example.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.KeluargaService;
import com.example.service.PendudukService;
import com.example.service.WilayahService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	WilayahService wilayahDAO;
	
	@RequestMapping("/")
	public String index () {
		return "index";
	}
	
	@RequestMapping("/penduduk")
	public String selectPenduduk (Model model, @RequestParam(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		log.info("Kecamatan {}", penduduk.getKecamatan());
		log.info("Kota {}", penduduk.getKota());
		model.addAttribute("penduduk", penduduk);
		return "view";
	}
	
	
	@RequestMapping("/keluarga")
	public String selectKeluarga (Model model, @RequestParam(value = "nkk") String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
		model.addAttribute("keluarga", keluarga);
		return "view-keluarga";
	}
	
	@RequestMapping("/keluarga/tambah")
	public String showAddKeluargaForm () {
		return "form-add-keluarga";
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String addKeluarga (Model model, @RequestParam(value = "alamat", required = true) String alamat,
			@RequestParam(value = "rt") String rt,
			@RequestParam(value = "rw") String rw,
			@RequestParam(value = "kelurahan") String kelurahan,
			@RequestParam(value = "kecamatan") String kecamatan,
			@RequestParam(value = "kota") String kota) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		Date currDate = new Date();
		String currDateOnly = "" + format.format(currDate);
		currDateOnly = currDateOnly.substring(0, 4) + "" + currDateOnly.substring(6);
		BigInteger idKelurahan = wilayahDAO.getKelurahanIDByName(kelurahan);
		BigInteger kodeKecamatan = wilayahDAO.getKecamatanKodeByName(kecamatan);
		String sequence = "0001";
		String searched = "" + kodeKecamatan + "" + currDateOnly + "%";
		
		int res = keluargaDAO.searchSequenceByNkk(searched.substring(0,6) + searched.substring(7));
		if(res != 0 ) {
			res += 1;
			sequence = String.format("%04d", res);
		}
		
		String nkk = searched.substring(0,6) + searched.substring(7,13) + sequence;
		KeluargaModel keluarga = new KeluargaModel (nkk, alamat, rt, rw, idKelurahan, false, kelurahan, kecamatan, kota);
		keluargaDAO.addKeluarga(keluarga);
		
		model.addAttribute("nkk", nkk);
		return "success-add-keluarga";
	}
	
	@RequestMapping("/penduduk/tambah")
	public String showAddPendudukForm () {
		return "form-add-penduduk";
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String addPenduduk (Model model, @RequestParam(value = "nama", required = true) String nama,
			@RequestParam(value = "tempat_lahir") String tempatLahir,
			@RequestParam(value = "tanggal_lahir") String tanggalLahir,
			@RequestParam(value = "jenis_kelamin") String jenisKelamin,
			@RequestParam(value = "gol_darah") String golDarah,
			@RequestParam(value = "agama") String agama,
			@RequestParam(value = "status_kawin") String statusKawin,
			@RequestParam(value = "pekerjaan") String pekerjaan,
			@RequestParam(value = "kewarganegaraan") String kewarganegaraan,
			@RequestParam(value = "status_wafat") String statusWafat,
			@RequestParam(value = "id_keluarga") BigInteger idKeluarga,
			@RequestParam(value = "status_dalam_keluarga") String statusDalamKeluarga) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date formattedTglLahir = formatter.parse(tanggalLahir);
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		
		String tanggalLahirFormat = format.format(formattedTglLahir);
		
		java.sql.Date inputTglLahir = new java.sql.Date(formatter.parse(tanggalLahir).getTime());
		
		String tglLahir = tanggalLahirFormat.substring(0, 4) + "" + tanggalLahirFormat.substring(6);
		int gender = 0;
		if(jenisKelamin.equalsIgnoreCase("perempuan")) {
			int tanggal = Integer.parseInt(tglLahir.substring(0, 2)) + 40;
			tglLahir = tanggal + "" + tglLahir.substring(2);
			gender = 1;
		}
		
		boolean isWni = false;
		if(kewarganegaraan.equals("WNI")) {
			isWni = true;
		}
		
		boolean isWafat = false;
		if(statusWafat.equalsIgnoreCase("wafat")) {
			isWafat = true;
		}
		
		String sequence = "0001";
		BigInteger kodeKecamatan = wilayahDAO.getKecamatanKodeByIDKeluarga(idKeluarga);
		
		String searched = "" + kodeKecamatan + "" + tglLahir + "%";
		int res = pendudukDAO.searchSequenceByNik(searched.substring(0,6) + searched.substring(7));
		if(res != 0 ) {
			res += 1;
			sequence = String.format("%04d", res);
		}
		
		String nik = searched.substring(0,6) + searched.substring(7,13) + sequence;
		
		PendudukModel penduduk = new PendudukModel(nik, nama, tempatLahir, inputTglLahir, gender, isWni, idKeluarga, 
													agama, pekerjaan, statusKawin, statusDalamKeluarga, golDarah, isWafat, 
													"", "", "", "", "", "");
		pendudukDAO.addPenduduk(penduduk);
		model.addAttribute("penduduk", penduduk);
		return "success-add-penduduk";
	}

	@RequestMapping("/penduduk/ubah/{nik}")
	public String showUbahPendudukForm (Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		String kewarganegaraan = "WNI";
		String isWafat = "Wafat";
		String jKelamin = "Laki-laki";
		
		if(!penduduk.isWni()) {
			kewarganegaraan = "WNA";
		}
		
		if(!penduduk.isWafat()) {
			isWafat = "Hidup";
		}
		
		if(penduduk.getJenisKelamin() == 1) {
			jKelamin = "Perempuan";
		}
		
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("kwn", kewarganegaraan);
		model.addAttribute("statWafat", isWafat);
		model.addAttribute("jKelamin", jKelamin);
		
		return "form-ubah-penduduk";
	}
	
	@RequestMapping(value="/penduduk/ubah/{nik}", method=RequestMethod.POST)
	public String ubahPenduduk (Model model, @PathVariable(value = "nik") String nik,
			@RequestParam(value = "nama", required = true) String nama,
			@RequestParam(value = "tempatLahir") String tempatLahir,
			@RequestParam(value = "tanggalLahir") String tanggalLahir,
			@RequestParam(value = "jKelamin") String jenisKelamin,
			@RequestParam(value = "golDarah") String golDarah,
			@RequestParam(value = "agama") String agama,
			@RequestParam(value = "statusKawin") String statusKawin,
			@RequestParam(value = "pekerjaan") String pekerjaan,
			@RequestParam(value = "kwn") String kewarganegaraan,
			@RequestParam(value = "statWafat") String statusWafat,
			@RequestParam(value = "idKeluarga") BigInteger idKeluarga,
			@RequestParam(value = "statusDalamKeluarga") String statusDalamKeluarga) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date formattedTglLahir = formatter.parse(tanggalLahir);
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		
		String tanggalLahirFormat = format.format(formattedTglLahir);
		
		java.sql.Date inputTglLahir = new java.sql.Date(formatter.parse(tanggalLahir).getTime());
		
		String tglLahir = tanggalLahirFormat.substring(0, 4) + "" + tanggalLahirFormat.substring(6);
		
		int gender = 0;
		if(jenisKelamin.equalsIgnoreCase("perempuan")) {
			
			gender = 1;
		}
		
		boolean isWni = false;
		if(kewarganegaraan.equals("WNI")) {
			isWni = true;
		}
		
		boolean isWafat = false;
		if(statusWafat.equalsIgnoreCase("wafat")) {
			isWafat = true;
		}
		
		PendudukModel oldPenduduk = pendudukDAO.selectPenduduk(nik);
		
		if(!oldPenduduk.getNama().equals(nama) 
			|| !oldPenduduk.getTempatLahir().equals(tempatLahir) 
			|| !oldPenduduk.getTanggalLahir().equals(inputTglLahir)
			|| oldPenduduk.getJenisKelamin() != gender 
			|| !oldPenduduk.getGolDarah().equals(golDarah)
			|| !oldPenduduk.getAgama().equals(agama)
			|| !oldPenduduk.getStatusKawin().equals(statusKawin)
			|| !oldPenduduk.getPekerjaan().equals(pekerjaan)
			|| oldPenduduk.isWni() != isWni
			|| oldPenduduk.isWafat() != isWafat
			|| oldPenduduk.getIdKeluarga() != idKeluarga
			|| !oldPenduduk.getStatusDalamKeluarga().equals(statusDalamKeluarga)) {
			
			String nik1 = nik;
			
			if(oldPenduduk.getJenisKelamin() != gender) {
				if(gender == 1) {
					int tanggal = Integer.parseInt(tglLahir.substring(0, 2)) + 40;
					tglLahir = tanggal + "" + tglLahir.substring(2);
				}
			}
			log.info("tglLahir untuk nik baru = {}", tglLahir);
			
			if(!oldPenduduk.getTanggalLahir().equals(inputTglLahir) || oldPenduduk.getJenisKelamin() != gender) {
				String sequence = "0001";
				BigInteger kodeKecamatan = wilayahDAO.getKecamatanKodeByIDKeluarga(idKeluarga);
				String searched = "" + kodeKecamatan + "" + tglLahir + "%";
				int res = pendudukDAO.searchSequenceByNik(searched.substring(0,6) + searched.substring(7));
				if(res != 0 ) {
					res += 1;
					sequence = String.format("%04d", res);
				}
				nik1 = searched.substring(0,6) + searched.substring(7,13) + sequence;
			}
			
			PendudukModel penduduk = new PendudukModel(nik1, nama, tempatLahir, inputTglLahir, gender, isWni, idKeluarga, 
														agama, pekerjaan, statusKawin, statusDalamKeluarga, golDarah, isWafat, 
														"", "", "", "", "", "");
			pendudukDAO.ubahPenduduk(penduduk, nik);
		}
		model.addAttribute("penduduk", oldPenduduk);
		return "success-update-penduduk";
	}
	
	@RequestMapping("/keluarga/ubah/{nkk}")
	public String showUbahKeluargaForm(Model model, @PathVariable(value = "nkk") String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
		String namaKelurahan = wilayahDAO.getKelurahanNameByID(keluarga.getIdKelurahan());
		keluarga.setKelurahan(namaKelurahan);
		model.addAttribute("keluarga", keluarga);
		return "form-ubah-keluarga";
	}
	
	@RequestMapping(value="/keluarga/ubah/{nkk}", method=RequestMethod.POST)
	public String ubahKeluarga (Model model, @PathVariable(value = "nkk") String nkk,
			@RequestParam(value = "alamat", required = true) String alamat,
			@RequestParam(value = "rt") String rt,
			@RequestParam(value = "rw") String rw,
			@RequestParam(value = "kelurahan") String kelurahan) throws ParseException {
		KeluargaModel oldKeluarga = keluargaDAO.selectKeluarga(nkk);
		
		BigInteger idKelurahan = wilayahDAO.getKelurahanIDByName(kelurahan);
		
		if(!oldKeluarga.getAlamat().equals(alamat)
			|| !oldKeluarga.getRt().equals(rt)
			|| !oldKeluarga.getRw().equals(rw)
			|| !oldKeluarga.getIdKelurahan().equals(idKelurahan)) {
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			Date currDate = new Date();
			String currDateOnly = "" + format.format(currDate);
			currDateOnly = currDateOnly.substring(0, 4) + "" + currDateOnly.substring(6);
			BigInteger kodeKecamatan = wilayahDAO.getKecamatanKodeByKelurahan(kelurahan);
			String sequence = "0001";
			String searched = "" + kodeKecamatan + "" + currDateOnly + "%";
			
			int res = keluargaDAO.searchSequenceByNkk(searched.substring(0,6) + searched.substring(7));
			if(res != 0 ) {
				res += 1;
				sequence = String.format("%04d", res);
			}
			
			String nkk1 = searched.substring(0,6) + searched.substring(7,13) + sequence;
			KeluargaModel keluarga = new KeluargaModel (nkk1, alamat, rt, rw, idKelurahan, false, "", "", "");
			keluargaDAO.ubahKeluarga(keluarga, nkk);
		}
			
		model.addAttribute("nkk", nkk);
		
		return "success-update-keluarga";
	}
	
	@RequestMapping(value="/penduduk/mati", method = RequestMethod.POST)
	public String deactivatePenduduk(Model model, @RequestParam("nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		pendudukDAO.deactivatePenduduk(nik);
		
		BigInteger idKeluarga = penduduk.getIdKeluarga();
		KeluargaModel keluarga = keluargaDAO.searchKeluargaByID(idKeluarga);
		
		if(keluargaDAO.countAnggotaHidup(keluarga.getNomorKk()) == 0) {
			keluargaDAO.deactivateKeluarga(keluarga.getNomorKk());
		}
		
		model.addAttribute("nik", nik);
		return "success-deactivate";
	}
	
	@RequestMapping(value="/penduduk/cari", method=RequestMethod.GET)
	public String showDropdowns(Model model,
								@RequestParam(value="kt", required=false) BigInteger idKota,
								@RequestParam(value="kc", required=false) BigInteger idKec,
								@RequestParam(value="kl", required=false) BigInteger idKel) {
		
		List<KotaModel> allKota = wilayahDAO.selectAllKota();
		model.addAttribute("allKota", allKota);
		if(idKota==null) {
			return "list-kota";
		} else {
			List<KecamatanModel> kecamatans = wilayahDAO.selectKecByKota(idKota);
			KotaModel kota = wilayahDAO.selectKota(idKota);
			model.addAttribute("kota", kota);
			model.addAttribute("kecamatans", kecamatans);
			
			if(idKec==null) {
				return "list-kec";
			} else {
				List<KelurahanModel> kelurahans = wilayahDAO.selectKelurahanByKecID(idKec);
				KecamatanModel kecamatan = wilayahDAO.selectKecamatan(idKec);
				model.addAttribute("kec", kecamatan);
				model.addAttribute("kelurahans", kelurahans);
				if(idKel==null) {
					return "list-kel";
				} else {
					KelurahanModel kelurahan = wilayahDAO.selectKelurahan(idKel);
					model.addAttribute("kel", kelurahan);
					List<PendudukModel> penduduks = wilayahDAO.selectPendudukInWilayah(idKel, idKec, idKota);
					model.addAttribute("penduduks", penduduks);
					return "penduduks-in-wilayah";
				}
			}
		}
		
//		if(idKota!=null && idKec==null && idKel==null) {
//			List<KecamatanModel> kecamatans = wilayahDAO.selectKecByKota(idKota);
//			String namaKota = wilayahDAO.getKotaNameByID(idKota);
//			model.addAttribute("nama_kota", namaKota);
//			model.addAttribute("kecamatans", kecamatans);
//			return "list-kec";
//		} else if(idKota!=null && idKec!=null && idKel==null ) {
//			List<KelurahanModel> kelurahans = wilayahDAO.selectKelurahanByKecID(idKec);
//			String namaKota = wilayahDAO.getKotaNameByID(idKota);
//			String namaKec = wilayahDAO.getKecNameByID(idKec);
//			model.addAttribute("nama_kota", namaKota);
//			model.addAttribute("nama_kec", namaKec);
//			model.addAttribute("kelurahans", kelurahans);
//			return "list-kel";
//		} else {
//			List<KotaModel> allKota = wilayahDAO.selectAllKota();
//			model.addAttribute("allKota", allKota);
//			return "list-kota";
//		}
	}
}
