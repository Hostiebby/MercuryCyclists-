package com.MercuryCyclists.Inventoryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

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

	@Bean    
    RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(InventoryServiceApplication.class);
	

  @Bean
  CommandLineRunner initDatabase(ProductRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Product(333, "Drifter v5", 125.99, "Turn handles and ride", 20)));
      log.info("Preloading " + repository.save(new Product(444, "Skid v2", 299.99, "comes with helmet", 25)));
      log.info("Preloading " + repository.save(new Product(555, "skidmark v8", 349.99, "For serious bikers", 15)));
    };
    
  }	
	

}
