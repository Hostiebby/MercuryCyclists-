package com.example.CSCI318Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;


import controllers.*;
import domainModels.*;
import repos.*;

@SpringBootApplication
@ComponentScan(basePackageClasses = { SupplierController.class, ContactController.class, ContactModelAssembler.class, SupplierModelAssembler.class })
@EnableJpaRepositories(basePackageClasses = { ContactRepository.class, SupplierRepository.class })
@EntityScan(basePackageClasses = { Contact.class, Supplier.class })
public class MercuryCyclistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercuryCyclistsApplication.class, args);
	}
	
	@Bean    
    RestTemplate restTemplate(){return new RestTemplate();}

}
