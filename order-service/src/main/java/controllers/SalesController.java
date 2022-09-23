package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domainModels.Sale;
import domainModels.SaleModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;

import dto.SaleRequest;
import repos.SaleRepository;
import service.SaleService;

@RestController
@RequestMapping("/sales")
public class SalesController {
	
	private final SaleService saleService;
	private final SaleRepository repository;
	private final SaleModelAssembler assembler;
	
	SalesController(SaleService saleService, SaleRepository repository, SaleModelAssembler assembler){
		this.saleService = saleService;
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeSale(@RequestBody SaleRequest saleRequest) {
		saleService.placeSale(saleRequest);
		return "Order placed successfully";
	}	
	
	
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public CollectionModel<EntityModel<Sale>> all() {
		List<EntityModel<Sale>> sales = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(sales, linkTo(methodOn(SalesController.class).all()).withSelfRel());
	}
	
	
	
	@GetMapping("/sales/{salesId}")
	public EntityModel<Sale> one(@PathVariable String salesId) {
		Sale sale = repository.findById(salesId) //
				.orElseThrow();
		return assembler.toModel(sale);
	}
	
	

}
