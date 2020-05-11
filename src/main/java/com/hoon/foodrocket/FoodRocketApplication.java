package com.hoon.foodrocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * "EnableAspectJAutoProxy"
 * AspectJ의 어노테이션을 사용하도록 지원한다.
 * 스프링의 <aop:aspectj-autoproxy> xml 태그와 유사한 기능을 지원한다.
 */
@EnableAspectJAutoProxy
@EnableRedisHttpSession
@SpringBootApplication
public class FoodRocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodRocketApplication.class, args);
	}

}
