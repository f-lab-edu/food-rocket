package com.hoon.foodrocket.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * "Aspect"
 * Aspect 역할을 명시한다.
 * 핵심 기능에서 부가 기능을 분리하기 위한 개념이다.
 * <p>
 * "Component"
 * 클래스나 인터페이스 단위에 사용할 수 있으며 빈을 등록할때 사용한다.
 * Aspect 클래스가 사용될 Controller 나 Service 는 모두 스프링 빈에 등록되어 관리되고 있습니다.
 * 하지만 Aspect 어노테이션에는 빈을 등록하기 위한 장치가 없기 때문에 Component 어노테이션을 사용해 빈을 등록시킵니다.
 */
@Aspect
@Component
public class LoginCheckAspect {
    Map<UserAuthorityLevel, LoginVerificationInterface> map;

    @Autowired
    public LoginCheckAspect(Map<UserAuthorityLevel, LoginVerificationInterface> map) {
        this.map = map;
    }

    /**
     * "Before"
     * 대상 메서드를 실행하기 전에 원하는 작업을 수행한다.
     */
    @Before("@annotation(com.hoon.foodrocket.aop.LoginType) && @annotation(loginType)")
    public void loginCheck(LoginType loginType) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        map.get(loginType.level()).loginVerification(session);
    }
}