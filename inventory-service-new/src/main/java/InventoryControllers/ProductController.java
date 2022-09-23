package InventoryControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import InventoryDomainModels.Part;
import InventoryDomainModels.Product;
import InventoryDomainModels.ProductModelAssembler;
import InventoryDomainModels.Product;
import InventoryExceptionHandlers.ProductNotFoundException;
import InventoryExceptionHandlers.ProductNotFoundException;
import InventoryRepos.PartRepository;
import InventoryRepos.ProductRepository;
import InventoryRepos.ProductRepository;

@RestController
public class ProductController{
	
	private final PartRepository partRepository;
	
	private final ProductRepository repository;
	private final ProductModelAssembler assembler;
	
	ProductController(PartRepository partRepository, ProductRepository repository, ProductModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
		this.partRepository = partRepository;
	}
	
	@GetMapping("/products")
	public CollectionModel<EntityModel<Product>> all() {
		List<EntityModel<Product>> products = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
	}
	
	@PostMapping("/products")
	ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
		Part newPart = partRepository.findById(newProduct.getPartId()).orElseThrow(() -> new ProductNotFoundException(newProduct.getPartId()));
		newProduct.setPart(newPart);
		
//		suppRepository.findById(newContact.getCompanyName()).orElseThrow(() -> new SupplierNotFoundException(newContact.getCompanyName())).pushContact(newContact);
//		newSupp.pushContact(newContact);
		
		EntityModel<Product> entityModel = assembler.toModel(repository.save(newProduct));		
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(EntityModel.of(entityModel));
	}	
	
	@GetMapping("/products/{productId}")
	public EntityModel<Product> one(@PathVariable Integer productId) {
		Product product = repository.findById(productId) //
				.orElseThrow(() -> new ProductNotFoundException(productId));
		return assembler.toModel(product);
	}
	
	@PutMapping("/products/{productId}")
	ResponseEntity<?> replaceContact(@RequestBody Product newProduct, @PathVariable Integer productId) {
		Product updatedProduct = repository.findById(productId) //
				.map(product -> {
					product.setProductId(newProduct.getProductId());
					product.setName(newProduct.getName());
					product.setPrice(newProduct.getPrice());
					product.setComment(newProduct.getComment());
					product.setPartId(newProduct.getPartId());
					return repository.save(product);
				}) //
				.orElseGet(() -> {
					newProduct.setProductId(productId);
					return repository.save(newProduct);
				});
		EntityModel<Product> entityModel = assembler.toModel(updatedProduct);
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	@DeleteMapping("/products/{productId}")
	ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
		repository.deleteById(productId);
		return ResponseEntity.noContent().build();
	}
}