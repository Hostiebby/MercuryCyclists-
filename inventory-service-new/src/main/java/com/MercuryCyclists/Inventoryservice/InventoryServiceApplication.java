package com.MercuryCyclists.Inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import InventoryControllers.PartController;
import InventoryControllers.ProductController;
import InventoryDomainModels.Part;
import InventoryDomainModels.PartModelAssembler;
import InventoryDomainModels.Product;
import InventoryDomainModels.ProductModelAssembler;
import InventoryRepos.PartRepository;
import InventoryRepos.ProductRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses = { PartController.class, PartModelAssembler.class, ProductController.class, ProductModelAssembler.class })
@EnableJpaRepositories(basePackageClasses = { PartRepository.class, ProductRepository.class })
@EntityScan(basePackageClasses = { Part.class, Product.class })
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
