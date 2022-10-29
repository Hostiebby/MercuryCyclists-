package InventoryControllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import InventoryDomainModels.Part;
import InventoryDomainModels.PartModelAssembler;
import InventoryDomainModels.Product;
import InventoryExceptionHandlers.PartNotFoundException;
import InventoryExceptionHandlers.ProductNotFoundException;
import InventoryRepos.PartRepository;
import InventoryRepos.ProductRepository;

@RestController
public class PartController{
	
	private final ProductRepository prodRepository;
	
	private final PartRepository repository;
	private final PartModelAssembler assembler;
	
	@Autowired
	private RestTemplate restTemplate;
	
	PartController(PartRepository repository, PartModelAssembler assembler, ProductRepository prodRepository) {
		this.repository = repository;
		this.assembler = assembler;
		this.prodRepository = prodRepository;
		
	} 
	
	@GetMapping("/parts")
	public CollectionModel<EntityModel<Part>> all() {
		List<EntityModel<Part>> parts = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(parts, linkTo(methodOn(PartController.class).all()).withSelfRel());
	}
	
	@PostMapping("/parts")
	ResponseEntity<?> newPart(@RequestBody Part newPart) {
		Product newProd = prodRepository.findById(newPart.getProductId()).orElseThrow(() -> new ProductNotFoundException(newPart.getProductId()));
		newPart.setProduct(newProd);
		
		EntityModel<Part> entityModel = assembler.toModel(repository.save(newPart));		
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(EntityModel.of(entityModel));
	}	
	
	@GetMapping("/parts/{partId}")
	public EntityModel<Part> one(@PathVariable Integer partId) {
		Part part = repository.findById(partId) //
				.orElseThrow(() -> new PartNotFoundException(partId));
		return assembler.toModel(part);
	}
	
	@PutMapping("/parts/{partId}")
	ResponseEntity<?> replacePart(@RequestBody Part newPart, @PathVariable Integer partId) {
		Part updatedPart = repository.findById(partId) //
				.map(part -> {
					part.setPartId(newPart.getpartId());
					part.setName(newPart.getName());
					part.setDescription(newPart.getDescription());					
					part.setCompanyName(newPart.getCompanyName());
					part.setStockOnHand(newPart.getStockOnHand());
					part.setProductId(newPart.getProductId());)
					return repository.save(part);
				}) //
				.orElseGet(() -> {
					newPart.setPartId(partId);
					return repository.save(newPart);
				});
		EntityModel<Part> entityModel = assembler.toModel(updatedPart);
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	//searching for the info on the supplier of a given part
	@GetMapping("/parts/{partId}/supplier")
	public Object supplier(@PathVariable Integer partId) {
		String url = "http://localhost:8080/suppliers/";
		Part part = repository.findById(partId) //
				.orElseThrow(() -> new PartNotFoundException(partId));
		return restTemplate.getForObject(url + part.getCompanyName(), Object.class);
	}
	
	@DeleteMapping("/parts/{PartId}")
	ResponseEntity<?> deletePart(@PathVariable Integer PartId) {
		repository.deleteById(PartId);
		return ResponseEntity.noContent().build();
	}
}