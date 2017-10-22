package com.example.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KelurahanModel;
import com.example.model.PendudukModel;

@Mapper
public interface KelurahanMapper {
	@Select("select id from kelurahan where nama_kelurahan=#{namaKelurahan}")
	BigInteger getIDByName(String namaKelurahan);
	
	@Select("select nama_kelurahan from kelurahan where id=#{kelurahan}")
	String getKelurahanNameByID(BigInteger kelurahan);
	
	@Select("select nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat,"
			+ " id_nama_kota.nama_kota, id_nama_kecamatan.nama_kecamatan, id_nama_kelurahan.nama_kelurahan, alamat, rt, rw"
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id = id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join (select id, id_kelurahan, alamat, rt, rw from keluarga) as id_alamat_keluarga on id_nama_kelurahan.id=id_alamat_keluarga.id_kelurahan"
			+ " join penduduk on penduduk.id_keluarga = id_alamat_keluarga.id"
			+ " where id_nama_kelurahan.id=#{idKel}"
			+ " and id_nama_kecamatan.id=#{idKec}"
			+ " and id_nama_kota.id=#{idKota}")
	List<PendudukModel> selectPendudukInWilayah(@Param("idKel") BigInteger idKel, @Param("idKec") BigInteger idKec, @Param("idKota") BigInteger idKota);

	@Select("select kelurahan.id, kode_kelurahan, id_kecamatan, nama_kecamatan, kode_pos, nama_kelurahan "
			+ "from (select id, nama_kecamatan from kecamatan) as id_nama_kecamatan "
			+ "join kelurahan on kelurahan.id_kecamatan=id_nama_kecamatan.id "
			+ "where kelurahan.id_kecamatan=#{idKec}")
	List<KelurahanModel> selectKelurahanByKecID(BigInteger idKec);

	@Select("select kelurahan.id, kode_kelurahan, id_kecamatan, nama_kecamatan, kode_pos, nama_kelurahan "
			+ "from (select id, nama_kecamatan from kecamatan) as id_nama_kecamatan "
			+ "join kelurahan on kelurahan.id_kecamatan=id_nama_kecamatan.id "
			+ "where kelurahan.id=#{idKel}")
	KelurahanModel selectKelurahan(BigInteger idKel);
}
