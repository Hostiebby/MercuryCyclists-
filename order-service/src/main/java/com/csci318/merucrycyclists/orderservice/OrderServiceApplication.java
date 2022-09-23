package com.csci318.merucrycyclists.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Config.WebClientConfig;
import controllers.SalesController;
import domainModels.ProductItems;
import domainModels.Sale;
import domainModels.SaleModelAssembler;
import dto.ProductItemsDto;
import dto.SaleRequest;
import repos.SaleRepository;
import service.SaleService;

@SpringBootApplication
@ComponentScan(basePackageClasses = { 
		SalesController.class, 
		ProductItemsDto.class, 
		SaleRequest.class, 
		SaleService.class,
		WebClientConfig.class,
		SaleModelAssembler.class
	})
@EnableJpaRepositories(basePackageClasses = { SaleRepository.class})
@EntityScan(basePackageClasses = { Sale.class, ProductItems.class})
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
