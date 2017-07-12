package cn.janescott.config;

import cn.janescott.common.Constants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by scott on 2017/6/6.
 * redis缓存配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    @Resource
    private Environment env;

    @Resource
    private EncryptConfig.RedisBean redisBean;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(env.getProperty(Constants.SPRING_REDIS_MAXIDLE)));
        config.setMinIdle(Integer.parseInt(env.getProperty(Constants.SPRING_REDIS_MINIDLE)));
        config.setMaxWaitMillis(Long.parseLong(env.getProperty(Constants.SPRING_REDIS_MAXWAIT)));
        config.setMaxTotal(Integer.parseInt(env.getProperty(Constants.SPRING_REDIS_MAXACTIVE)));
        return config;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory factory =  new JedisConnectionFactory(jedisPoolConfig());
        factory.setHostName(redisBean.getHost());
        factory.setPort(Integer.parseInt(redisBean.getPort()));
        return factory;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(){
        return new StringRedisTemplate(redisConnectionFactory());
    }

    @Bean(name = "redisTemplate")
    @SuppressWarnings({"unchecked","rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        // 序列化
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置序列化
        template.setValueSerializer(jsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(){
        String [] cacheNames = {"sidebars", "user"};
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate(), Arrays.asList(cacheNames));
        cacheManager.setDefaultExpiration(1800);
        return cacheManager;
    }

}
