package com.kafein.envanter.core.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafein.envanter.core.persistence.entity.AbstractEntity;
import com.kafein.envanter.core.service.AbstractService;

@SuppressWarnings("rawtypes")
@RestController
@Controller
public abstract class AbstractController<Entity extends AbstractEntity, Service extends AbstractService> {

	public abstract Service getService();
	public Logger log = LoggerFactory.getLogger(this.getClass());


	@PostMapping
	public ResponseEntity<AbstractEntity> save(@RequestBody  Entity entity) {
		@SuppressWarnings("unchecked")
		AbstractEntity save = getService().save(entity);
		return ResponseEntity.ok(save);
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<AbstractEntity> get( @PathVariable Long id) {
		AbstractEntity abstractEntity = getService().get(id);
		return ResponseEntity.ok(abstractEntity);

	}

	@DeleteMapping({"/{id}"})
	public ResponseEntity delete( @PathVariable Long id) {
		try {
			getService().remove(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@GetMapping({"/all"})
	public ResponseEntity<List<Entity>> getAll() {
		@SuppressWarnings("unchecked")
		List<Entity> all = getService().getAll();
		return ResponseEntity.ok(all);
	}
	
	@PostMapping({ "/search" })
	public ResponseEntity<Page<Entity>> search(@RequestBody  Entity entity)
			throws IOException {

		entity.setCreatedDate(null);
		entity.setLastModifiedDate(null);


		@SuppressWarnings("unchecked")
		Page<Entity> result = getService().search(entity);

		return ResponseEntity.ok(result);
	}
		
}

