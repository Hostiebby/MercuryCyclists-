package com.csci318.merucrycyclists.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import controllers.SalesController;
import domainModels.Sale;
import domainModels.SaleModelAssembler;
import repos.SaleRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses = { 
		SalesController.class,		
		SaleModelAssembler.class
	})
@EnableJpaRepositories(basePackageClasses = { SaleRepository.class})
@EntityScan(basePackageClasses = { Sale.class })
public class OrderServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@Bean    
    RestTemplate restTemplate(){return new RestTemplate();}

}
