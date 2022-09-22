package com.MercuryCyclists.Inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import InventoryControllers.PartController;
import InventoryDomainModels.Part;
import InventoryDomainModels.PartModelAssembler;
import InventoryRepos.PartRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses = { PartController.class, PartModelAssembler.class })
@EnableJpaRepositories(basePackageClasses = { PartRepository.class })
@EntityScan(basePackageClasses = { Part.class })
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
