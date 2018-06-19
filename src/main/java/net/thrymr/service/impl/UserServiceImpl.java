package net.thrymr.service.impl;

import java.util.List;

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
import net.thrymr.model.Student;
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
  
  public String signin(LoginBean loginBean) {
    try {
    	System.out.println("signin ================================== ==== ");     
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBean.getUserName(), loginBean.getPassword()));
      AppUser appUser = userRepository.findByUsername(loginBean.getUserName());
      Assert.notNull(appUser, "User doesn't exist");
      return jwtTokenProvider.createToken(loginBean.getUserName(), appUser.getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser user) {
	  System.out.println("signup ================================== ==== ");
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

public List<AppUser> getUsers(Authentication authentication) {
	
	Assert.notNull(authentication.getPrincipal(), "User doesn't exist");
	
	Student student = new Student(
								  "Eng2015001", "John Doe", Student.Gender.MALE, 1);
								   studentRepository.save(student);
	
	Student retrievedStudent = studentRepository.findById("Eng2015001").get();
			
	System.out.println(retrievedStudent.toString());
	
	
	return userRepository.findAll();
}

}