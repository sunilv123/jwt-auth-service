package net.thrymr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.thrymr.model.AppUser;
import net.thrymr.repository.CustomerRepository;
import net.thrymr.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
	 // final AppUser user = userRepository.findByUsername(username);
	  
	  System.out.println("username : "+username);
	  
	  final AppUser user = customerRepository.findAppUser(username);
	
    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }
    
      /*final AppUser user = redisRepositoryImpl.getAppUser(username); 
	  System.out.println(username+"  user :::  "+user);
	  Assert.notNull(user, "User '" + username + "' not found");*/

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(user.getPassword())//
        .authorities(user.getRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
