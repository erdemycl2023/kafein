package com.kafein.envanter.kategori;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kafein.envanter.core.persistence.entity.AbstractEntity;


@Entity(name = "kategori")
@Table(name = "kategori")

public class KategoriEntity extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@Column
	private String ad;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

}
