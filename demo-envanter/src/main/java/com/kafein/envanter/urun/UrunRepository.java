package com.kafein.envanter.urun;

import java.util.List;

import com.kafein.envanter.core.persistence.repository.AbstractRepository;


public interface UrunRepository extends AbstractRepository<UrunEntity> {
	List<UrunEntity> findByAdContainingOrderByAd( String ad);
}