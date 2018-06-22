package net.thrymr.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

/*  @Bean
  public ErrorAttributes errorAttributes() {
    // Hide exception field in the return object
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        errorAttributes.remove("exception");
        return errorAttributes;
      }
    };
  }*/

  @ExceptionHandler(CustomException.class)
  public void handleCustomException(CustomException e, HttpServletResponse res, CustomException ex) throws IOException {
	  e.printStackTrace();
    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(AccessDeniedException e, HttpServletResponse res) throws IOException {
	  e.printStackTrace();
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ExceptionHandler(Exception.class)
  public void handleException(Exception e, HttpServletResponse res) throws IOException {
	  e.printStackTrace();
    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
  }

}
