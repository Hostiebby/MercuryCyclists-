package com.csci318.merucrycyclists.orderservice;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.MessageChannel;
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
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);
	

	  @Bean
	  CommandLineRunner initDatabase(SaleRepository repository) {

	    return args -> {
	      log.info("New sale " + repository.save(new Sale(333, 2)));
	      log.info("New Sale " + repository.save(new Sale(444, 2)));
	      log.info("New Sale " + repository.save(new Sale(555, 2)));
	    };
	    
	  }	
	

}


