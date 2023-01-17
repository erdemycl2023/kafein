package com.kafein.envanter.kategori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafein.envanter.core.service.AbstractService;

@Service
public class KategoriService extends AbstractService<KategoriEntity> {

	@Autowired
	private KategoriRepository  kategoriRepository;


	@Override
	public KategoriRepository getRepository() {
		return  kategoriRepository;
	}

}