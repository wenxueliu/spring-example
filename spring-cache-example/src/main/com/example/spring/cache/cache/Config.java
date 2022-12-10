package com.example.spring.cache.cache;


import com.example.spring.cache.service.UserService;
import com.example.spring.cache.service.impl.UserServiceImpl;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class Config {
    @Bean
    public CacheManager cacheManger(RedisConnectionFactory redisConnectionFactory) {
        //通过 Config 对象即可对缓存进行自定义配置
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                // 禁止缓存 null 值
                .disableCachingNullValues()
                // 设置 key 序列化
                .serializeKeysWith(keyPair())
                // 设置 value 序列化
                .serializeValuesWith(valuePair())
                // 设置缓存前缀
                .prefixCacheNameWith("cache:scrd:")
                // 设置过期时间
                .entryTtl(Duration.ofMinutes(10L));
        RedisCacheManager.RedisCacheManagerBuilder cacheManagerBuilder = RedisCacheManager.builder(redisConnectionFactory);
        //设置默认的配置，当设置测cacheName没有配置的时候，使用默认配置
        cacheManagerBuilder.cacheDefaults(cacheConfig);

        //entryTtl方法不是在原有对象中修改配置
        //而是会返回一个新的RedisCacheConfiguration对象，需要用cacheConfig接收。否则设置无效。
        cacheConfig = cacheConfig.entryTtl(Duration.ofMinutes(720L));

        //设置cacheName对呀的配置
        cacheManagerBuilder.withCacheConfiguration("product", cacheConfig);

        // 返回 Redis 缓存管理器
        return cacheManagerBuilder.build();
    }

    /**
     * 配置键序列化
     *
     * @return StringRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
    }

    /**
     * 配置值序列化，使用 GenericJackson2JsonRedisSerializer 替换默认序列化
     *
     * @return GenericJackson2JsonRedisSerializer
     */
    private RedisSerializationContext.SerializationPair<Object> valuePair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
    }

    @Bean("userKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName() + "[" + Arrays.asList(params) + "]";
            }
        };
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}
