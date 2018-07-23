package net.thrymr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sunil.bean.AppConstants;
import net.sunil.bean.Customer;
import net.sunil.bean.GenericResponse;
import net.sunil.bean.LoginBean;
import net.thrymr.repository.CustomerRepository;
import net.thrymr.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
@Api(tags = "Users API")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	 @ApiOperation(value = "${UserController.signup}", response = GenericResponse.class)
	public GenericResponse signup(@RequestBody LoginBean loginBean) {

		 return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, userService.signup(loginBean));
	}
  
	@PostMapping(value = "/update-user")
	public GenericResponse updateUser(@RequestBody LoginBean loginBean) {
		 return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, userService.updateUser(loginBean));
	}
	
	@PostMapping(value = "/add-author")
	public GenericResponse addAuthor(@RequestBody LoginBean loginBean) {
		 return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, userService.addAuthor(loginBean));
	}
	
  @RequestMapping(value= "/signin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  @ApiOperation(value = "${UserController.signin}", response = GenericResponse.class)
  public GenericResponse login(@ApiParam("Signin User") @RequestBody LoginBean loginBean)throws Exception {
    return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, userService.signin(loginBean));
  }
  
  @RequestMapping(value= "/get-users", method = RequestMethod.GET, produces = "application/json")
  @ApiOperation(value = "${UserController.getUsers}", response = GenericResponse.class)
  public GenericResponse getUsers(Authentication authentication)throws Exception {
    return  new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS,userService.getUsers(authentication));
  }


}
