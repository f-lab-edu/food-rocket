package com.hoon.foodrocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * "EnableAspectJAutoProxy"
 * AspectJ의 어노테이션을 사용하도록 지원한다.
 * 스프링의 <aop:aspectj-autoproxy> xml 태그와 유사한 기능을 지원한다.
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class FoodRocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodRocketApplication.class, args);
	}

}
