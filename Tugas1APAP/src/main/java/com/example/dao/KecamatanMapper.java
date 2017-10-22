package com.example.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {
	@Select("select kode_kecamatan from kecamatan where nama_kecamatan=#{namaKecamatan}")
	BigInteger getKodeByName(String namaKecamatan);
	
	@Select("select kode_kecamatan from kecamatan "
			+ "join (select id, id_kecamatan from kelurahan) as s_kelurahan on kecamatan.id = s_kelurahan.id_kecamatan "
			+ "join (select id, id_kelurahan from keluarga) as s_keluarga on s_kelurahan.id = s_keluarga.id_kelurahan "
			+ "where s_keluarga.id = #{idKeluarga}")
	BigInteger getKecamatanKodeByIDKeluarga(BigInteger idKeluarga);
	
	@Select("select kode_kecamatan from kecamatan "
			+ "join (select nama_kelurahan, id_kecamatan from kelurahan) as id_nama_kelurahan "
			+ "on kecamatan.id=id_nama_kelurahan.id_kecamatan "
			+ "where id_nama_kelurahan.nama_kelurahan=#{kelurahan}")
	BigInteger getKecamatanKodeByKelurahan(String kelurahan);
	
	@Select("select kecamatan.id, kode_kecamatan, id_kota, nama_kecamatan "
			+ "from (select id from kota) as kota_id join kecamatan "
			+ "on kecamatan.id_kota=kota_id.id "
			+ "where kota_id.id=#{idKota}")
	List<KecamatanModel> selectKecByKota(@Param("idKota") BigInteger idKota);
	
	@Select("select nama_kecamatan from kecamatan where id=#{idKec}")
	String getKecNameByID(@Param("idKec") BigInteger idKec);
	
	@Select("select kecamatan.id, kode_kecamatan, id_kota, nama_kecamatan "
			+ "from (select id from kota) as kota_id join kecamatan "
			+ "on kecamatan.id_kota=kota_id.id "
			+ "where kecamatan.id=#{idKec}")
	KecamatanModel selectKecamatan(BigInteger idKec);
}
