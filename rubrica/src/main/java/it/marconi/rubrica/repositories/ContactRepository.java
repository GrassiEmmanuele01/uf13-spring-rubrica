package it.marconi.rubrica.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import it.marconi.rubrica.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID>{
    
}
