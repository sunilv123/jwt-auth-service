package net.thrymr.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.sunil.bean.Customer;
import net.thrymr.model.AppUser;
import net.thrymr.repository.CustomerRepository;
 
 
@Service
public class CustomerRepositoryImpl implements CustomerRepository {
 
	private static final String KEY = "Customer";
	
	private static final String KEY_APPUSER = "AppUser";
 
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Customer> hashOperations;
	private HashOperations<String, String, AppUser> hashOperationsAppUser;
 
	@Autowired
	public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
		hashOperationsAppUser = redisTemplate.opsForHash();
	}
 
	@Override
	public void save(Customer customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public Customer find(Long id) {
		return hashOperations.get(KEY, id);
	}
 
	@Override
	public Map<Long, Customer> findAll() {
		return hashOperations.entries(KEY);
	}
 
	@Override
	public void update(Customer customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY, id);
	}

	@Override
	public void saveAppUser(AppUser appUser) {
		hashOperationsAppUser.put(KEY_APPUSER, appUser.getUsername(), appUser);
		
	}

	@Override
	public AppUser findAppUser(String userName) {
		return hashOperationsAppUser.get(KEY_APPUSER, userName);
	}

	@Override
	public Map<String, AppUser> findAllAppUser() {
		return hashOperationsAppUser.entries(KEY_APPUSER);
	}

	@Override
	public void update(AppUser appUser) {
		hashOperationsAppUser.put(KEY_APPUSER, appUser.getEmail(), appUser);
		
	}

	@Override
	public void deleteAppUser(Long id) {
		hashOperationsAppUser.delete(KEY_APPUSER, id);		
	}
 
}