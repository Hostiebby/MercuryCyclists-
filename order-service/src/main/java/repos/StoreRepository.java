package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import domainModels.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{

}
