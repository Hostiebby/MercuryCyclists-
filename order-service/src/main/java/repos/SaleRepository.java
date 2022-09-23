package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import domainModels.Sale;

public interface SaleRepository extends JpaRepository<Sale, String>{

}
