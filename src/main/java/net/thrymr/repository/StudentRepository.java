package net.thrymr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.thrymr.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
