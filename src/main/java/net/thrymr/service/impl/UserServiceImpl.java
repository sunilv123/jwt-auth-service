package net.thrymr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import net.sunil.bean.LoginBean;
import net.thrymr.exception.CustomException;
import net.thrymr.model.AppUser;
import net.thrymr.model.Role;
import net.thrymr.model.Student;
import net.thrymr.repository.CustomerRepository;
import net.thrymr.repository.StudentRepository;
import net.thrymr.repository.UserRepository;
import net.thrymr.security.JwtTokenProvider;
import net.thrymr.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;
 
  @Autowired
  StudentRepository studentRepository;
  
  @Autowired
  private CustomerRepository customerRepository;
  
  public String signin(LoginBean loginBean) {
	  
    	  System.out.println("signin ================================== ==== ");     
    	//  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBean.getUserName(), loginBean.getPassword()));
	      AppUser appUser = userRepository.findByUsername(loginBean.getUserName());
	      Assert.notNull(appUser, "User doesn't exist");
	      
	      customerRepository.saveAppUser(appUser);
	      
	      System.out.println(appUser.getEmail() + " ====== "+customerRepository.findAppUser(appUser.getEmail()));
	      
	      
	      return jwtTokenProvider.createToken(loginBean.getUserName(), appUser.getRoles());
  }

  public String signup(LoginBean loginBean) {
	  System.out.println("signup ================================== ==== ");
    if (!userRepository.existsByUsername(loginBean.getUserName())) {
    	
      AppUser user1 = new AppUser();
    	
      user1.setPassword(passwordEncoder.encode(loginBean.getPassword()));
      user1.setUsername(loginBean.getUserName());
      user1.setEmail(loginBean.getEmail());
      List<Role> role = new ArrayList<Role>();
      role.add(Role.ROLE_ADMIN);
      user1.setRoles(role);
      userRepository.save(user1);
      
      customerRepository.saveAppUser(user1);
      
      for (int i = 0; i < 500; i++) {
		
    	  AppUser user = new AppUser();
      	
          user.setPassword(passwordEncoder.encode(loginBean.getPassword()));
          user.setUsername(loginBean.getUserName()+i);
          user.setEmail(loginBean.getEmail()+i);
          user.setRoles(role);
          userRepository.save(user);
    	  
          customerRepository.saveAppUser(user);
	  }
      
      
      return jwtTokenProvider.createToken(user1.getUsername(), user1.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

public List<AppUser> getUsers(Authentication authentication) {
	
	Assert.notNull(authentication.getPrincipal(), "User doesn't exist");
	
/*	Student student = new Student(
								  "Eng20154", "John Doe", Student.Gender.MALE, 1);
								   studentRepository.save(student);
	
	List<Student> retrievedStudent = studentRepository.findAll();
	System.out.println(retrievedStudent.size());
*/
	/*userRepository.findAll().forEach(user->{
		customerRepository.saveAppUser(user);
	});*/
	
	/*Map<String, AppUser> list = customerRepository.findAllAppUser();*/
	List<AppUser> list = userRepository.findAll();
	
	System.out.println("User list "+list.size());
	
	return list;
}

}
