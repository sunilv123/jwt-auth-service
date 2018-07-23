package net.thrymr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
/*@RedisHash("AppUser")*/
@Audited
public class AppUser  implements Serializable{
	
	  /**
		 * 
		 */
	  private static final long serialVersionUID = -2582050017159786286L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	  @Column(unique = true, nullable = false)
	  private String username;
	
	  @Column(unique = true, nullable = false)
	  private String email;
	
	  @Size(min = 8, message = "Minimum password length: 8 characters")
	  private String password;
	
	  @NotAudited
	  @ElementCollection(fetch = FetchType.EAGER)
	  private List<Role> roles  = new ArrayList<>();


	  @OneToMany(mappedBy="appUser")
	  private List<Author> authorList = new ArrayList<>();
}
