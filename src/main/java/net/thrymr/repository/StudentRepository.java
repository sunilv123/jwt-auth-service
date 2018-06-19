package net.thrymr.repository;

import org.springframework.data.repository.CrudRepository;

import net.thrymr.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

}
