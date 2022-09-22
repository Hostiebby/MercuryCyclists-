package domainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import controllers.SupplierController;

@Component
public class SupplierModelAssembler implements RepresentationModelAssembler<Supplier, EntityModel<Supplier>> {

  @Override
  public EntityModel<Supplier> toModel(Supplier supplier) {

    return EntityModel.of(supplier, //
        linkTo(methodOn(SupplierController.class).one(supplier.getCompanyName())).withSelfRel(),
        linkTo(methodOn(SupplierController.class).all()).withRel("suppliers"));
  }
}