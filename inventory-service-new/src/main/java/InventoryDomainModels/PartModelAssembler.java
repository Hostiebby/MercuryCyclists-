package InventoryDomainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import InventoryControllers.PartController;

@Component
public class PartModelAssembler implements RepresentationModelAssembler<Part, EntityModel<Part>> {

  @Override
  public EntityModel<Part> toModel(Part part) {

    return EntityModel.of(part, //
        linkTo(methodOn(PartController.class).one(part.getpartId())).withSelfRel(),
        linkTo(methodOn(PartController.class).all()).withRel("parts"));
  }
}