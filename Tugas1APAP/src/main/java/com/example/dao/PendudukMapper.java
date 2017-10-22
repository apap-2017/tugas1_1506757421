package com.example.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select("select nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat,"
			+ " id_nama_kota.nama_kota, id_nama_kecamatan.nama_kecamatan, id_nama_kelurahan.nama_kelurahan, alamat, rt, rw"
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id = id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join (select id, id_kelurahan, alamat, rt, rw from keluarga) as id_alamat_keluarga on id_nama_kelurahan.id=id_alamat_keluarga.id_kelurahan"
			+ " join penduduk on penduduk.id_keluarga = id_alamat_keluarga.id"
			+ " where nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Insert("insert into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat)"
			+ " values(#{nik}, #{nama}, #{tempatLahir}, #{tanggalLahir}, #{jenisKelamin}, #{isWni}, #{idKeluarga}, #{agama}, #{pekerjaan}, #{statusKawin}, #{statusDalamKeluarga}, #{golDarah}, #{isWafat})")
	void addPenduduk(PendudukModel penduduk);
	
	@Select("select count(id) from penduduk where nik like #{substrNik}")
	int searchSequenceByNik(@Param("substrNik") String substrNik);
	
	@Update("update penduduk set nik=#{penduduk.nik}, nama=#{penduduk.nama}, tempat_lahir=#{penduduk.tempatLahir}, tanggal_lahir=#{penduduk.tanggalLahir}, "
			+ "jenis_kelamin=#{penduduk.jenisKelamin}, is_wni=#{penduduk.isWni}, id_keluarga=#{penduduk.idKeluarga}, agama=#{penduduk.agama}, pekerjaan=#{penduduk.pekerjaan}, "
			+ "status_perkawinan=#{penduduk.statusKawin}, status_dalam_keluarga=#{penduduk.statusDalamKeluarga}, golongan_darah=#{penduduk.golDarah} "
			+ "where nik=#{oldNik}")
	void ubahPenduduk(@Param("penduduk") PendudukModel penduduk, @Param("oldNik") String oldNik);
	
	@Update("update penduduk set is_wafat=1 where nik=#{nik}")
	void deactivatePenduduk(@Param("nik") String nik);
	
	@Select("select nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat,"
			+ " id_nama_kota.nama_kota, id_nama_kecamatan.nama_kecamatan, id_nama_kelurahan.nama_kelurahan, alamat, rt, rw"
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id = id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join (select id, id_kelurahan, alamat, rt, rw from keluarga) as id_alamat_keluarga on id_nama_kelurahan.id=id_alamat_keluarga.id_kelurahan"
			+ " join penduduk on penduduk.id_keluarga = id_alamat_keluarga.id"
			+ " where id_nama_kelurahan.id=#{idKel}")
	List<PendudukModel> selectPendudukInWilayah(@Param("idKel") BigInteger idKec, BigInteger idKel);
}
