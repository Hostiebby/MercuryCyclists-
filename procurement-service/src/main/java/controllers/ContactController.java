package controllers;

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

import domainModels.Contact;
import domainModels.ContactModelAssembler;
import domainModels.Supplier;
import exceptionHandlers.ContactNotFoundException;
import exceptionHandlers.SupplierNotFoundException;
import repos.ContactRepository;
import repos.SupplierRepository;

@RestController
public class ContactController{
	
	private final SupplierRepository suppRepository;
	
	private final ContactRepository repository;
	private final ContactModelAssembler assembler;
	
	ContactController(SupplierRepository suppRepository, ContactRepository repository, ContactModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
		this.suppRepository = suppRepository;
	}
	
	@GetMapping("/contacts")
	public CollectionModel<EntityModel<Contact>> all() {
		List<EntityModel<Contact>> contacts = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(contacts, linkTo(methodOn(ContactController.class).all()).withSelfRel());
	}
	
	@PostMapping("/contacts")
	ResponseEntity<?> newContact(@RequestBody Contact newContact) {
		Supplier newSupp = suppRepository.findById(newContact.getCompanyName()).orElseThrow(() -> new SupplierNotFoundException(newContact.getCompanyName()));
		newContact.setSupplier(newSupp);
		
//		suppRepository.findById(newContact.getCompanyName()).orElseThrow(() -> new SupplierNotFoundException(newContact.getCompanyName())).pushContact(newContact);
//		newSupp.pushContact(newContact);
		
		EntityModel<Contact> entityModel = assembler.toModel(repository.save(newContact));		
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(EntityModel.of(entityModel));
	}	
	
	@GetMapping("/contacts/{name}")
	public EntityModel<Contact> one(@PathVariable String name) {
		Contact contact = repository.findById(name) //
				.orElseThrow(() -> new ContactNotFoundException(name));
		return assembler.toModel(contact);
	}
	
	@PutMapping("/contacts/{name}")
	ResponseEntity<?> replaceContact(@RequestBody Contact newContact, @PathVariable String name) {
		Contact updatedContact = repository.findById(name) //
				.map(contact -> {
					contact.setName(newContact.getName());
					contact.setPhone(newContact.getPhone());
					contact.setEmail(newContact.getEmail());
					contact.setPosition(newContact.getPosition());
					contact.setCompanyName(newContact.getCompanyName());
					return repository.save(contact);
				}) //
				.orElseGet(() -> {
					newContact.setName(name);
					return repository.save(newContact);
				});
		EntityModel<Contact> entityModel = assembler.toModel(updatedContact);
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	@DeleteMapping("/contacts/{name}")
	ResponseEntity<?> deleteContact(@PathVariable String name) {
		repository.deleteById(name);
		return ResponseEntity.noContent().build();
	}
}