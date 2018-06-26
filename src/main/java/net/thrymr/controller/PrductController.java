package net.thrymr.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.sunil.bean.AppConstants;
import net.sunil.bean.GenericResponse;

@RestController
@RequestMapping("/product")
@Api(tags = "Product API")
public class PrductController {


  @RequestMapping(value= "/get-product", method = RequestMethod.GET, produces = "application/json")
  public GenericResponse getUsers(Authentication authentication) {
    return  new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, "Something went wrong");
  }


}
