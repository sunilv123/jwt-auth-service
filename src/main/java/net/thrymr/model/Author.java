package net.thrymr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Setter
@Getter
public class Author{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	private AppUser appUser;
	
}
