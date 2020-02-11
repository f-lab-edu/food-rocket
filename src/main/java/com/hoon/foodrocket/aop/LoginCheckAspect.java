package com.hoon.foodrocket.aop;

import com.hoon.foodrocket.util.HttpSessionUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * "Aspect"
 * Aspect 역할을 명시한다.
 * 핵심 기능에서 부가 기능을 분리하기 위한 개념이다.
 *
 * "Component"
 * 클래스나 인터페이스 단위에 사용할 수 있으며 빈을 등록할때 사용한다.
 */
@Aspect
@Component
public class LoginCheckAspect {
    /**
     * "Before"
     * 대상 메서드를 실행하기 전에 원하는 작업을 수행한다.
     */
    @Before("@annotation(com.hoon.foodrocket.aop.CheckOwnerLogin)")
    public void checkOwnerLogin(){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        if (loginOwnerEmail == null) {
            throw new IllegalStateException("로그인(사장)이 필요합니다.");
        }
    }

}
