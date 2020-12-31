package com.mypro.system.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @program: redis-code
 * @author: 雷哥
 * @create: 2020-01-02 14:42
 **/



@Configuration
public class RedisConfig {
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
//        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
//        RedisCacheConfiguration config = RedisCacheConfiguration
//                .defaultCacheConfig();
//
//        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
//                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        if (redisProperties.getTimeToLive() != null) {
//            config = config.entryTtl(redisProperties.getTimeToLive());
//        }
//        if (redisProperties.getKeyPrefix() != null) {
//            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
//        }
//        if (!redisProperties.isCacheNullValues()) {
//            config = config.disableCachingNullValues();
//        }
//        if (!redisProperties.isUseKeyPrefix()) {
//            config = config.disableKeyPrefix();
//        }
//        return config;
//    }

}
