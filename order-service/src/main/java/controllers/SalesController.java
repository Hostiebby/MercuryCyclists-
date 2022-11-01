package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import domainModels.Sale;
import domainModels.SaleModelAssembler;
import exceptionhandlers.SaleNotFoundException;
import repos.SaleRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class SalesController {	
	
	private final SaleRepository repository;
	private final SaleModelAssembler assembler;
	
	@Autowired
	private RestTemplate restTemplate;
	
	SalesController(SaleRepository repository, SaleModelAssembler assembler){		
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@PostMapping("/sales")
	//@ResponseStatus(HttpStatus.CREATED)	
	ResponseEntity<?> newSale(@RequestBody Sale newSale) {
		String productUrl = "http://localhost:8081/products/";
		ResponseEntity<Map> getProduct = restTemplate.getForEntity(productUrl + newSale.getProductId(), Map.class);
		Map<?, ?> productMap = getProduct.getBody();
		int productStock = (int) productMap.get("stockOnHand");
		if(productStock >= 1) {
			
		}
		else {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Error: No stock on hand");
		}
		
				
		/*String partUrl = "http://localhost:8081/products/";
		ResponseEntity<Part[]> getPart = restTemplate.getForEntity(partUrl + newSale.getProductId() + "/parts", Part[].class);
		Part[] part = getPart.getBody();*/
		//Map<?, ?> partMap = getPart.getBody();
			
		
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
	
	//searching for the product info by sale
	@GetMapping("/sales/{salesId}/products")
	public Object Product(@PathVariable Integer salesId) {
		String url = "http://localhost:8081/products/";
		Sale sale = repository.findById(salesId) //
				.orElseThrow(() -> new SaleNotFoundException(salesId));
		return restTemplate.getForObject(url + sale.getProductId(), Object.class);
	}
	
	

}
