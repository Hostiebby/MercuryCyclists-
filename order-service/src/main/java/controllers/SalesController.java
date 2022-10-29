package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import domainModels.Sale;
import domainModels.SaleModelAssembler;
import repos.SaleRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

@RestController
//@RequestMapping("/sales")
public class SalesController {	
	
	private final SaleRepository repository;
	private final SaleModelAssembler assembler;
	
	SalesController(SaleRepository repository, SaleModelAssembler assembler){		
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@PostMapping("/sales")
	//@ResponseStatus(HttpStatus.CREATED)	
	ResponseEntity<?> newProduct(@RequestBody Sale newSale) {
		  
		EntityModel<Sale> entityModel = assembler.toModel(repository.save(newSale));
		  
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	
	
	@GetMapping("/sales")
	//@ResponseStatus(HttpStatus.OK)
	public CollectionModel<EntityModel<Sale>> all() {
		List<EntityModel<Sale>> sales = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(sales, linkTo(methodOn(SalesController.class).all()).withSelfRel());
	}
	
	
	
	@GetMapping("/sales/{salesId}")
	public EntityModel<Sale> one(@PathVariable Integer salesId) {
		Sale sale = repository.findById(salesId) //
				.orElseThrow();
		return assembler.toModel(sale);
	}	
	
	

}
