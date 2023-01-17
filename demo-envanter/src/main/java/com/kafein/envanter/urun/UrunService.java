package com.kafein.envanter.urun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafein.envanter.core.service.AbstractService;

@Service
public class UrunService extends AbstractService<UrunEntity> {

	@Autowired
	private UrunRepository  urunRepository;

	@Autowired
	private UrunHistoryService  urunHistoryService;

	@Override
	public UrunRepository getRepository() {
		return  urunRepository;
	}


	public void history(UrunEntity entity, String islem) {
		UrunHistoryEntity urunHistoryEntity = new UrunHistoryEntity();
		urunHistoryEntity.setRefrenceId(entity.getId());
		urunHistoryEntity.setAd(entity.getAd());
		urunHistoryEntity.setKategori(entity.getKategori());
		urunHistoryEntity.setSayi(entity.getSayi());
		urunHistoryEntity.setIslem(islem);
		urunHistoryService.save(urunHistoryEntity);
				
	}

}