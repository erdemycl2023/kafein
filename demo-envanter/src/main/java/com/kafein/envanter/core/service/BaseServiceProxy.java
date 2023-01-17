package com.kafein.envanter.core.service;

import com.kafein.envanter.core.persistence.entity.AbstractEntity;
import com.kafein.envanter.core.persistence.repository.AbstractRepository;

public abstract class BaseServiceProxy<Entity extends AbstractEntity, Repository extends AbstractRepository<Entity>> {

    public abstract Repository getRepository();

}
