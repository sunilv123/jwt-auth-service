package net.thrymr.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;

import net.sunil.bean.LoginBean;
import net.thrymr.model.AppUser;

public interface UserService {

	public String signin(LoginBean loginBean);

	/*public String signup(AppUser user);*/

	public List<AppUser> getUsers(Authentication authentication);

}
