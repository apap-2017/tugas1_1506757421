package com.example.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KotaModel;

@Mapper
public interface KotaMapper {
	@Select("select kode_kota from kota where nama_kota=#{kota}")
	BigInteger getKodeByName(String kota);
	
	@Select("select id, kode_kota, nama_kota from kota")
	List<KotaModel> selectAllKota();
	
	@Select("select nama_kota from kota where id=#{idKota}")
	String getKotaNameByID(@Param("idKota") BigInteger idKota);
	
	@Select("select id, kode_kota, nama_kota from kota where id=#{idKota}")
	KotaModel selectKota(BigInteger idKota);
}
