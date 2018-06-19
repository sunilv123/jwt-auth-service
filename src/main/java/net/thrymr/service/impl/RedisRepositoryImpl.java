package net.thrymr.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import net.thrymr.model.AppUser;
import net.thrymr.model.Student;
import net.thrymr.service.RedisRepository;

@Service
public class RedisRepositoryImpl implements RedisRepository{

	private static final String KEY = "Movie"; 
	
    private RedisTemplate<String, Object> redisTemplate;
    
    private HashOperations<String, Object, Object> hashOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    
    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    
    public void add(final Student student) {
        hashOperations.put(KEY, student.getId(), student.getName());
    }
    
    public void add(final AppUser user) {
        hashOperations.put(KEY, user.getId(), user.toString());
    }
    
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }
    
    public Object findById(final String id){
        return  hashOperations.get(KEY, id);
    }
    
    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }
	
}
