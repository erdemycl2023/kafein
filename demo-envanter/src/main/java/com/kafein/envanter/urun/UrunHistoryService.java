package com.kafein.envanter.urun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafein.envanter.core.service.AbstractService;

@Service
public class UrunHistoryService extends AbstractService<UrunHistoryEntity> {

	@Autowired
	private UrunHistoryRepository  urunHistoryRepository;


	@Override
	public UrunHistoryRepository getRepository() {
		return  urunHistoryRepository;
	}

}