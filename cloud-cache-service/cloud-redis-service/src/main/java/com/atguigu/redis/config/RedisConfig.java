package com.atguigu.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * redis配置
 *
 * @author ruoyi
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {
//    @Value("${spring.redis.cluster.nodes}")
//    private String nodes;

//    @Value("${spring.redis.cluster.socketTimeout}")
//    private int socketTimeout;
//
//    @Value("${spring.redis.cluster.connectionTimeOut}")
//    private int connectionTimeOut;
//
//    @Value("${spring.redis.cluster.maxAttempts}")
//    private int maxAttempts;

//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private long maxWait;
//
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;


    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

//    //创建redis连接工厂
//    public JedisConnectionFactory jedisConnectionFactory() {
//        //集群模式
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration(), poolConfig());
//        factory.afterPropertiesSet();
//        return factory;
//    }
//
//    //redis配置
//    public JedisPoolConfig poolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
//        return jedisPoolConfig;
//    }
//
//
//    //redis集群配置,6个ip以,分割，然后再以:分割
//    public RedisClusterConfiguration redisClusterConfiguration() {
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        String[] cNodes = nodes.split(",");
//        Set<RedisNode> hp = new HashSet<>();
//        for (String node : cNodes) {
//            String[] split = node.split(":");
//            hp.add(new RedisNode(split[0].trim(), Integer.valueOf(split[1])));
//        }
//        redisClusterConfiguration.setPassword(password);
//        redisClusterConfiguration.setClusterNodes(hp);
//        return redisClusterConfiguration;
//    }
}
