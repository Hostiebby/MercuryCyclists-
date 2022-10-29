package domainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import controllers.PartController;

@Component
public class PartModelAssembler implements RepresentationModelAssembler<Part, EntityModel<Part>> {

  @Override
  public EntityModel<Part> toModel(Part part) {

    return EntityModel.of(part, //
        linkTo(methodOn(PartController.class).one(part.getName())).withSelfRel(),
        linkTo(methodOn(PartController.class).all()).withRel("parts"));
  }
}