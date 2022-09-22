package InventoryRepos;

import org.springframework.data.jpa.repository.JpaRepository;

import InventoryDomainModels.Part;

public interface PartRepository extends JpaRepository<Part, String> {

}