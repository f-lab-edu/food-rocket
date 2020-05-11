package com.hoon.foodrocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
public class RedisConfig {
    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    /**
     * Redis는 직렬화된 데이터를 메모리 서버에 저장하는 형태를 가진 시스템으로 key, value는 직렬화가 필요하다.
     * <p>
     * StringRedisSerializer는 단순 문자열을 바이트 배열로 변환할 수 있고, 기본 charset이 UTF-8이다.
     * key값의 경우 주로 문자열을 사용하기 때문에 StringRedisSerializer를 사용했다.
     * <p>
     * GenericJackson2JsonRedisSerializer은 동적 타입을 사용하여 객체를 json에 매핑한다.
     * 만약 redis에 저장할 객체가 특정적일 경우라면 Jackson2JsonRedisSerializer를 사용하는 것이 더 명확하다.
     * <p>
     * ObjectMapper는 자바 객체를 json 형태로 직렬화하거나, json을 자바 객체로 역직렬화하는 작업을 수행한다.
     * readValue - json to java object
     * writeValue - java object to json
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(new ObjectMapper()));

        return redisTemplate;
    }
}
