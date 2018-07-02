package net.thrymr.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
	  
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
	      
	      //Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
	      
	      template.setConnectionFactory( jedisConnectionFactory() );
	      //template.setKeySerializer( new StringRedisSerializer() );
	     // template.setHashValueSerializer( jackson2JsonRedisSerializer );
	     // template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	     // template.setValueSerializer(jackson2JsonRedisSerializer);
	      template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	      return template;
	
		  
	  }

	  @Bean
	  ChannelTopic topic() {
	      return new ChannelTopic("messageQueue");
	  }


}

