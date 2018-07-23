package net.thrymr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.thrymr.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
