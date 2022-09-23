package InventoryDomainModels;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import InventoryControllers.ProductController;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

  @Override
  public EntityModel<Product> toModel(Product product) {

    return EntityModel.of(product, //
        linkTo(methodOn(ProductController.class).one(product.getProductId())).withSelfRel(),
        linkTo(methodOn(ProductController.class).all()).withRel("products"));
  }
}