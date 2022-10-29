package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import domainModels.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

}