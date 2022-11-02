package domainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import controllers.StoreController;

@Component
public class StoreModelAssembler implements RepresentationModelAssembler<Store, EntityModel<Store>> {
	@Override
	  public EntityModel<Store> toModel(Store store) {

	    return EntityModel.of(store, //
	        linkTo(methodOn(StoreController.class).one(store.getStoreId())).withSelfRel(),
	        linkTo(methodOn(StoreController.class).all()).withRel("stores"));
	  }
}