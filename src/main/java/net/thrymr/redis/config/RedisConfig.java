package net.thrymr.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class RedisConfig {
	  
	
	@Value("${redis.host}")
    private String redisHost;
 
    @Value("${redis.port}")
    private Integer redisPort;
 
    @Value("${redis.pass}")
    private String redisPass;
	
	  @Bean
	  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	  }

	    @Bean
	    @Primary
	    JedisConnectionFactory jedisConnectionFactory() throws Exception {
	    	
	    	 RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
	         redisStandaloneConfiguration.setHostName(redisHost);
	         redisStandaloneConfiguration.setPort(redisPort);
	        /* redisStandaloneConfiguration.setDatabase(0);*/
	         redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPass));

	         JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//	         jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

	         JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
	                 jedisClientConfiguration.build());
	    	
	         return jedisConFactory;
	    }

	@Bean
	  RedisTemplate< String, Object > redisTemplate() throws Exception {
		  
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
	  
	  @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                .apis(RequestHandlerSelectors.basePackage("net.thrymr.controller"))
	               /* .paths(PathSelectors.regex("/.*"))*/
	                .build();

	    }


}

