package person.marlon.springbootdemo.common.cache.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfiguration {

//	@Bean//By default, the bean name managed by the container will be the same as the method name.
//	public LettuceConnectionFactory redisConnectionFactory() {

		//非连接池设置
//		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//				.useSsl().and()
//				.commandTimeout(Duration.ofSeconds(2))
//				.shutdownTimeout(Duration.ZERO)
//				.build();
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("cache_redis_server", 6379);
//		redisStandaloneConfiguration.setDatabase(1);//设置database为1
//		return new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);

		//连接池设置
//		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//		genericObjectPoolConfig.setMaxIdle();
//		genericObjectPoolConfig.setMaxTotal();
//		genericObjectPoolConfig.setMaxWaitMillis();
//		genericObjectPoolConfig.setBlockWhenExhausted();
//		LettuceClientConfiguration poolingClientConfiguration = LettucePoolingClientConfiguration.builder()
//				.poolConfig(genericObjectPoolConfig)
//				.commandTimeout(Duration.ofSeconds(2))
//				.shutdownTimeout(Duration.ZERO)
//				.build();
//		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("cache_redis_server", 6379),poolingClientConfiguration);
//	}



	/**
	 * 自定义模板<String, Serializable>
	 */
	@Bean
	public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		//template.setValueSerializer(new RedisObjectSerializer());//自定义反序列化器
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
