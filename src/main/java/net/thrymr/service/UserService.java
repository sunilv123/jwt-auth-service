package net.thrymr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import net.thrymr.repository.UserRepository;
import net.thrymr.security.JwtTokenProvider;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(LoginBean loginBean) {
    try {
    	System.out.println(loginBean.getUserName()+" :: "+loginBean.getPassword());
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBean.getUserName(), loginBean.getPassword()));
      AppUser appUser = userRepository.findByUsername(loginBean.getUserName());
      Assert.notNull(appUser, "User doesn't exist");
      return jwtTokenProvider.createToken(loginBean.getUserName(), appUser.getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public AppUser search(String username) {
    AppUser user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public AppUser whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

public List<AppUser> getUsers(Authentication authentication) {
	
	Assert.notNull(authentication.getPrincipal(), "User doesn't exist");
	
	return userRepository.findAll();
}

}
