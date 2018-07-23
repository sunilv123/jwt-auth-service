package net.sunil.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginBean {

	private Long id;
	
	private Long authorId;
	
	private String name;
	
	private String userName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String email;
	
	private String mobile;
	
	private String token;

}
