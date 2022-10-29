package domainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import controllers.SalesController;

@Component
public class SaleModelAssembler implements RepresentationModelAssembler<Sale, EntityModel<Sale>> {
	@Override
	  public EntityModel<Sale> toModel(Sale sale) {

	    return EntityModel.of(sale, //
	        linkTo(methodOn(SalesController.class).one(sale.getSalesId())).withSelfRel(),
	        linkTo(methodOn(SalesController.class).all()).withRel("sales"));
	  }
}
