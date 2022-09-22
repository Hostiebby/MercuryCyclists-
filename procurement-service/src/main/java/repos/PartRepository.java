package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import domainModels.Part;

public interface PartRepository extends JpaRepository<Part, String> {

}