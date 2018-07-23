package net.thrymr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching 
public class JwtAuthServiceApp implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

	@Bean
	public RestTemplate rest() {
	 return new RestTemplate();
	}
  
  @Override
  public void run(String... params) throws Exception {
    /*AppUser admin = new AppUser();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

    userService.signup(admin);

    AppUser client = new AppUser();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);*/
  }

}
