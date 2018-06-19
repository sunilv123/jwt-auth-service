package net.thrymr;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import net.thrymr.model.AppUser;
import net.thrymr.model.Role;
import net.thrymr.service.impl.UserServiceImpl;

@SpringBootApplication
@EnableCaching 
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserServiceImpl userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
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
  
  
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {

  	JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
    
      return jedisConFactory;
  }

  @Bean
  RedisTemplate< String, Object > redisTemplate() {
      final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
      template.setConnectionFactory( jedisConnectionFactory() );
      template.setKeySerializer( new StringRedisSerializer() );
      template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
      template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
      return template;
  }

  @Bean
  ChannelTopic topic() {
      return new ChannelTopic("messageQueue");
  }
}
