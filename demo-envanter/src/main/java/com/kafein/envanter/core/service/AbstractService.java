package com.kafein.envanter.core.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kafein.envanter.core.persistence.entity.AbstractEntity;
import com.kafein.envanter.core.persistence.repository.AbstractRepository;

@Transactional
public abstract class AbstractService<Entity extends AbstractEntity>
		extends BaseServiceProxy<Entity,  AbstractRepository<Entity>> {

	public long count() {
		return getRepository().count();
	}

	public Entity save(Entity entity) {
		return getRepository().save(entity);
	}

	public Entity get(Long id) {
		Optional<Entity> optional = getRepository().findById(id);
		if (optional.isPresent()) {
			Entity entity = optional.get();
			return entity;
		}
		return null;
	}

	public void remove(Long id) {
		getRepository().deleteById(id);
	}

	public List<Entity> getAll() {
		List<Entity> all = getRepository().findAll(Sort.by("createdDate").descending());
		return all;
	}

	public List<Entity> getAll(Sort sort) {
		List<Entity> all = getRepository().findAll(sort);
		return all;
	}

	public Page<Entity> search(int currentPage, int pageSize) {
		Page<Entity> all = getRepository().findAll(PageRequest.of(currentPage, pageSize, Sort.by("createdDate").descending()));
		return all;
	}

	public Page<Entity> search(int currentPage, int pageSize, Sort sort) {
		Page<Entity> all = getRepository().findAll(PageRequest.of(currentPage, pageSize, sort));
		return all;
	}

	public Page<Entity> search(Entity entity) {
		Example<Entity> example = Example.of(entity, ExampleMatcher.matchingAll().withIgnoreCase().
				withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		int currentPage = 0;
		if(entity.getCurrentpage() != null)
			currentPage = entity.getCurrentpage().intValue();
		Pageable sortedByName = PageRequest.of(currentPage, 20, Sort.by("createdDate"));
		Page<Entity> resultList = getRepository().findAll(example,sortedByName);
		return resultList;
	}
	
	
}

