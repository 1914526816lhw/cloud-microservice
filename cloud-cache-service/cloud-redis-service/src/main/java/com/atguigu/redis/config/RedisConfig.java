package com.atguigu.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import io.lettuce.core.resource.ClientResources;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * redis配置
 *
 * @author ruoyi
 */
@Configuration
@EnableCaching
@PropertySource("redis-cluster.properties")
public class RedisConfig extends CachingConfigurerSupport {


    // pool
    @Value("${redis.pool.maxActive}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.minIdle}")
    private int minIdle;
    @Value("${redis.pool.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${redis.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;
    @Value("${redis.pool.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${redis.pool.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${redis.pool.softMinEvictableIdleTimeMillis}")
    private long softMinEvictableIdleTimeMillis;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.pool.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${redis.pool.testOnReturn}")
    private boolean testOnReturn;
    @Value("${redis.pool.blockWhenExhausted}")
    private boolean blockWhenExhausted;

    // cluster
    @Value("${redis.cluster.host}")
    private String host;
    @Value("${redis.cluster.port}")
    private String port;
    @Value("${redis.cluster.socketTimeout}")
    private int socketTimeout;
    @Value("${redis.cluster.connectionTimeOut}")
    private int connectionTimeOut;
    @Value("${redis.cluster.maxAttempts}")
    private int maxAttempts;
    @Value("${redis.cluster.maxRedirects}")
    private int maxRedirects;
    @Value("${redis.password}")
    private String password;


    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        //redis连接工厂
        LettuceConnectionFactory connectionFactory = getLettuceConnectionFactory();

        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    private LettuceConnectionFactory getLettuceConnectionFactory() {
        return new LettuceConnectionFactory(getRedisCusterConfiguration());
    }

    private RedisClusterConfiguration getRedisCusterConfiguration() {
        RedisClusterConfiguration config = new RedisClusterConfiguration();
        config.setPassword(password);
        return config;
    }


}
