package net.thrymr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Country{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String currencyCode;
	
	@NotNull
	private String currencyName;
	
	private String currencySymbol;
	
	private String flag;

	@NotNull
	private String callingCode;
	
	private String numericCode;
	
}
