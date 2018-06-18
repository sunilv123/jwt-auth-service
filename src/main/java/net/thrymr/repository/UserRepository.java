package net.thrymr.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.thrymr.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);
  
  AppUser findByEmail(String email);

  @Transactional
  void deleteByUsername(String username);

}
