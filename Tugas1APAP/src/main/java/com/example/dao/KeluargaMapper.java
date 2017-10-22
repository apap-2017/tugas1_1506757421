package com.example.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select("select nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku, nama_kelurahan, nama_kecamatan, nama_kota"
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id=id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join keluarga on id_nama_kelurahan.id=keluarga.id_kelurahan"
			+ " where nomor_kk = #{nkk}")
	@Results(value = {
		@Result(property="nomorKk", column="nomor_kk"),
		@Result(property="alamat", column="alamat"),
		@Result(property="rt", column="rt"),
		@Result(property="rw", column="rw"),
		@Result(property="idKelurahan", column="id_kelurahan"),
		@Result(property="isTidakBerlaku", column="is_tidak_berlaku"),
		@Result(property="kelurahan", column="nama_kelurahan"),
		@Result(property="kecamatan", column="nama_kecamatan"),
		@Result(property="kota", column="nama_kota"),
		@Result(property="anggota", column="nomor_kk", 
				javaType=List.class, 
				many=@Many(select="selectAnggota"))
	})
	KeluargaModel selectKeluarga (@Param("nkk") String nkk);
	
	@Select("select nik, nama, tempat_lahir, (CASE WHEN tanggal_lahir!='0000-00-00' THEN tanggal_lahir END), jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat,"
			+ "nama_kota, nama_kecamatan, nama_kelurahan, alamat, rt, rw "
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id = id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join (select id, nomor_kk, id_kelurahan, alamat, rt, rw from keluarga) as id_alamat_keluarga on id_nama_kelurahan.id=id_alamat_keluarga.id_kelurahan"
			+ " join penduduk on penduduk.id_keluarga = id_alamat_keluarga.id"
			+ " where nomor_kk = #{nkk}")
	List<PendudukModel> selectAnggota (@Param("nkk") String nkk);
	
	@Insert("insert into keluarga (nomor_kk, alamat, rt, rw, id_kelurahan)"
			+ " values(#{nomorKk}, #{alamat}, #{rt}, #{rw}, #{idKelurahan})")
	void addKeluarga(KeluargaModel keluarga);
	
	@Select("select count(id) from keluarga where nomor_kk like #{substrNkk}")
	int searchSequenceByNkk(@Param("substrNkk") String substrNkk);
	
	@Update("update keluarga set nomor_kk=#{keluarga.nomorKk}, alamat=#{keluarga.alamat}, rt=#{keluarga.rt}, rw=#{keluarga.rw}, "
			+ "id_kelurahan=#{keluarga.idKelurahan} "
			+ "where nomor_kk=#{oldNkk}")
	void ubahKeluarga(@Param("keluarga") KeluargaModel keluarga, @Param("oldNkk") String oldNkk);
	
	@Select("select nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku, nama_kelurahan, nama_kecamatan, nama_kota"
			+ " from (select id, nama_kota from kota) as id_nama_kota "
			+ " join (select id, id_kota, nama_kecamatan from kecamatan) as id_nama_kecamatan on id_nama_kota.id=id_nama_kecamatan.id_kota"
			+ " join (select id, id_kecamatan, nama_kelurahan from kelurahan) as id_nama_kelurahan on id_nama_kecamatan.id=id_nama_kelurahan.id_kecamatan"
			+ " join keluarga on id_nama_kelurahan.id=keluarga.id_kelurahan"
			+ " where keluarga.id = #{id}")
	@Results(value = {
		@Result(property="nomorKk", column="nomor_kk"),
		@Result(property="alamat", column="alamat"),
		@Result(property="rt", column="rt"),
		@Result(property="rw", column="rw"),
		@Result(property="idKelurahan", column="id_kelurahan"),
		@Result(property="isTidakBerlaku", column="is_tidak_berlaku"),
		@Result(property="kelurahan", column="nama_kelurahan"),
		@Result(property="kecamatan", column="nama_kecamatan"),
		@Result(property="kota", column="nama_kota"),
		@Result(property="anggota", column="nomor_kk", 
				javaType=List.class, 
				many=@Many(select="selectAnggota"))
	})
	KeluargaModel searchKeluargaByID (@Param("id") BigInteger id);
	
	@Select("select count(*) from (select nomor_kk, id from keluarga) as nkk_id"
			+ " join (select nik, id_keluarga, id, is_wafat from penduduk) as stat_wafat"
			+ " on nkk_id.id=stat_wafat.id_keluarga"
			+ " where stat_wafat.is_wafat=0 and nomor_kk=#{nkk}")
	int countAnggotaHidup(@Param("nkk") String nkk);
	
	@Update("update keluarga set is_tidak_berlaku=1 where nomor_kk=#{nkk}")
	void deactivateKeluarga (@Param("nkk") String nkk);
}
