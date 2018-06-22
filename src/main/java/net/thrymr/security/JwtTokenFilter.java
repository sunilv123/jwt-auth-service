package net.thrymr.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.sunil.bean.AppConstants;
import net.sunil.bean.GenericResponse;

public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }


@Override
protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	
	 System.out.println("Before calling doFilter ------ ");
	  
	try { 
	 
	  String bearerToken = req.getHeader("X-Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	     String token = bearerToken.replaceAll("Bearer ", "");
	   
	     if (token != null && jwtTokenProvider.validateToken(token)) {
	    	  Authentication auth = jwtTokenProvider.getAuthentication(token);
	    	  SecurityContextHolder.getContext().setAuthentication(auth);
	    	  filterChain.doFilter(req, response);
	    }
	    }else {
	    	throw new SecurityException("Authentication failed, please login to continue.");
	    }
	    
	}catch (Exception e) {
		  ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		   GenericResponse resp = new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, "AUTH_TOKEN_EXPIRED");
		   String jsonRespString = ow.writeValueAsString(resp);
		   response.setContentType("application/json");
		   response.getWriter().write(jsonRespString);
		   response.getWriter().flush();
		   response.getWriter().close();
	}
}
/*public boolean validateToken(String token) {
    try {
    	
      System.out.println("token : "+token+" :  "+secretKey);
      secretKey =  Base64.getEncoder().encodeToString(secretKey.getBytes());
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
    	throw new SecurityException("Token expired, please login to continue.");
    }
  }


public Authentication getAuthentication(String token) {
	try {
     UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
     return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}catch (Exception e) {
		throw new SecurityException("Token is tempered, please login to continue.");
	}
  }

public String getUsername(String token)  {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
}*/


}
