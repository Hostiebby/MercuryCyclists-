package repos;

import org.springframework.data.jpa.repository.JpaRepository;

import domainModels.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {

}