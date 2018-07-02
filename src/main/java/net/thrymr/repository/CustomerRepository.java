package net.thrymr.repository;

import java.util.Map;

import net.sunil.bean.Customer;
import net.thrymr.model.AppUser;

public interface CustomerRepository {
	 
	void save(Customer customer);
	
	Customer find(Long id);
	
	Map<Long, Customer> findAll();
	
	void update(Customer customer);
	
	void delete(Long id);
	
	//Appuser
	
    void saveAppUser(AppUser appUser);
	
	AppUser findAppUser(String userName);
	
	Map<String, AppUser> findAllAppUser();
	
	void update(AppUser appUser);
	
	void deleteAppUser(Long id);
	
}
