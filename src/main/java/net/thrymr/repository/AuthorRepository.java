package net.thrymr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.thrymr.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
