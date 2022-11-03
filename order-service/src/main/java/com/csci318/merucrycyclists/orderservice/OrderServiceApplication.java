package com.csci318.merucrycyclists.orderservice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import controllers.StoreController;
import domainModels.Sale;
import domainModels.SaleModelAssembler;
import domainModels.Store;
import domainModels.StoreModelAssembler;
import repos.SaleRepository;
import repos.StoreRepository;
import rx.Observable;
import rx.schedulers.Schedulers;

@SpringBootApplication
@ComponentScan(basePackageClasses = { 
		SalesController.class,		
		SaleModelAssembler.class,
		StoreController.class,
		StoreModelAssembler.class
	})
@EnableJpaRepositories(basePackageClasses = { SaleRepository.class, StoreRepository.class})
@EntityScan(basePackageClasses = { Sale.class, Store.class })
public class OrderServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		
		
	}
	
	@Bean    
    RestTemplate restTemplate(){return new RestTemplate();}
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);
	
	List<Integer> getProduct = Arrays.asList(333, 444, 555);	
	

	  @Bean
	  CommandLineRunner initDatabase(SaleRepository saleRepository, StoreRepository storeRepository) {

	    return args -> {
	      log.info("Initialise store " + storeRepository.save(new Store("somewhere st", "Greg")));
	      
	      //Integer rProduct = getProduct.get(new Random().nextInt(getProduct.size()));
	  	
	  	  //Integer rQuantity = Math.random() > .5 ? 1:5;
	      
	      Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
	      		.observeOn(Schedulers.newThread())
	      		.subscribe(s -> log.info("New sale " + saleRepository.save(new Sale(getProduct.get(new Random().nextInt(getProduct.size())), Math.random() > .5 ? 1:5, 1))));
	      		
	      //log.info("New sale " + saleRepository.save(new Sale(rProduct, rQuantity, 1)));
	      //log.info("New Sale " + repository.save(new Sale(444, 2)));
	      //log.info("New Sale " + repository.save(new Sale(555, 2)));
	    };
	    
	  }	
	

}


