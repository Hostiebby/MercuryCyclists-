package InventoryRepos;

import org.springframework.data.jpa.repository.JpaRepository;

import InventoryDomainModels.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}