package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import controllers.SalesController;
import domainModels.Sale;
import domainModels.Store;
import domainModels.StoreModelAssembler;
import exceptionhandlers.StoreNotFoundException;
import repos.StoreRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONArray;


import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class StoreController {
	
	private final StoreRepository repository;
	private final StoreModelAssembler assembler;
	
	StoreController(StoreRepository repository, StoreModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@PostMapping("/stores")
	ResponseEntity<?> newStore(@RequestBody Store newStore) {
		  
		EntityModel<Store> entityModel = assembler.toModel(repository.save(newStore));
		  
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	@GetMapping("/stores")
	public CollectionModel<EntityModel<Store>> all() {
		List<EntityModel<Store>> stores = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(stores, linkTo(methodOn(StoreController.class).all()).withSelfRel());
	}
	
	@GetMapping("/stores/{storeId}")
	public EntityModel<Store> one(@PathVariable Integer storeId) {
		Store store = repository.findById(storeId) //
				.orElseThrow();
		return assembler.toModel(store);
	}	
}