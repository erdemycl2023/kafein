package com.kafein.envanter.urun;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kafein.envanter.core.persistence.entity.AbstractEntity;
import com.kafein.envanter.kategori.KategoriEntity;



@Entity(name = "urunhistory")
@Table(name = "urunhistory")

public class UrunHistoryEntity extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@Column
	private long refrenceId;
	
	
	public long getRefrenceId() {
		return refrenceId;
	}

	public void setRefrenceId(long refrenceId) {
		this.refrenceId = refrenceId;
	}

	@Column
	private String islem;

	public String getIslem() {
		return islem;
	}

	public void setIslem(String islem) {
		this.islem = islem;
	}

	@Column
	private String ad;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategoriid")
	private KategoriEntity kategori;

	
	@Column
	private String sayi;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public KategoriEntity getKategori() {
		return kategori;
	}

	public void setKategori(KategoriEntity kategori) {
		this.kategori = kategori;
	}

	public String getSayi() {
		return sayi;
	}

	public void setSayi(String sayi) {
		this.sayi = sayi;
	}
	
}
