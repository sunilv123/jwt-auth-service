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
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
