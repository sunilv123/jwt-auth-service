package net.thrymr.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.thrymr.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);
  
  Optional<AppUser> findByEmail(String email);

  @Transactional
  void deleteByUsername(String username);

}
