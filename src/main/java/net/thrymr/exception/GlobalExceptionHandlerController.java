package net.thrymr.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.bean.AppConstants;
import net.sunil.bean.GenericResponse;

@ControllerAdvice
@RestController
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
  public GenericResponse handleCustomException(CustomException e, HttpServletResponse res, CustomException ex) throws IOException {
	  e.printStackTrace();
   // res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
			ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(AccessDeniedException e, HttpServletResponse res) throws IOException {
	  e.printStackTrace();
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public GenericResponse handleException(final Exception e) {
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				"Oops! Something went wrong, please try again");
	}

}
