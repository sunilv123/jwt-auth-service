package net.thrymr.service;

import java.util.Map;

import net.thrymr.model.AppUser;
import net.thrymr.model.Student;

public interface RedisRepository {

    Map<Object, Object> findAllMovies(); 
    
    void add(Student student);
    
    void add(AppUser user);
    
    void delete(String id);
    
    Object findById(String id);
	
}
