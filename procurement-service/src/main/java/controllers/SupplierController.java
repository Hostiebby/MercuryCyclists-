package controllers;

import java.util.List;
import java.util.Set;
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

import domainModels.Contact;
import domainModels.Supplier;
import domainModels.SupplierModelAssembler;
import exceptionHandlers.SupplierNotFoundException;
import repos.SupplierRepository;
import services.ApiServices;
import services.Part;

@RestController
public class SupplierController{
	
	private final SupplierRepository repository;
	
	private final SupplierModelAssembler assembler;
	
	private ApiServices apiServices;
	
	SupplierController(SupplierRepository repository, SupplierModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/suppliers")
	public CollectionModel<EntityModel<Supplier>> all() {
		List<EntityModel<Supplier>> suppliers = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList()); 
		
		return CollectionModel.of(suppliers, linkTo(methodOn(SupplierController.class).all()).withSelfRel());
	}
	
	@PostMapping("/suppliers")
	ResponseEntity<?> newSupplier(@RequestBody Supplier newSupplier) {
		  
		EntityModel<Supplier> entityModel = assembler.toModel(repository.save(newSupplier));
		  
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	@GetMapping("/suppliers/{companyName}")
	public EntityModel<Supplier> one(@PathVariable String companyName) {

		Supplier supplier = repository.findById(companyName) 
				.orElseThrow(() -> new SupplierNotFoundException(companyName));

	    return assembler.toModel(supplier);
	}

	@PutMapping("/suppliers/{companyName}")
	ResponseEntity<?> replaceSupplier(@RequestBody Supplier newSupplier, @PathVariable String companyName) {
	    
		Supplier updatedSupplier = repository.findById(companyName) //
				.map(supplier -> {
					supplier.setCompanyName(newSupplier.getCompanyName());
					supplier.setBase(newSupplier.getBase());
					return repository.save(supplier);
				}) //
				.orElseGet(() -> {
					newSupplier.setCompanyName(companyName);
					return repository.save(newSupplier);
				});
	    EntityModel<Supplier> entityModel = assembler.toModel(updatedSupplier);
	    
	    return ResponseEntity //
	    		.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	    		.body(entityModel);
	    }
	
	//Contact	
	@GetMapping("/suppliers/{companyName}/contacts")
	public Set<Contact> contacts(@PathVariable String companyName) {
		return repository.findById(companyName)
				.orElseThrow(() -> new SupplierNotFoundException(companyName))
				.displayContacts();
	}
	
	@PutMapping("suppliers/{companyName}/contacts/{name}")

	  @DeleteMapping("/suppliers/{companyName}")
	  ResponseEntity<?> deleteSupplier(@PathVariable String companyName) {
	    repository.deleteById(companyName);
	    return ResponseEntity.noContent().build();
	  }
	
	//Part request
	/*@GetMapping("/suppliers/parts/{partId}")
	public Supplier partSupplier(@PathVariable("partId") Integer partId){
		Part getPart = apiServices.getPart(partId);
		return repository.findById(getPart.getCompanyName()).get();				
				
	}*/
	  
}

