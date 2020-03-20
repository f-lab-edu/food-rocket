package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.Owner;
import com.hoon.foodrocket.mapper.OwnerMapper;
import com.hoon.foodrocket.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * "Service"
 * 해당 클래스가 Service 클래스임을 명시한다.
 * 비지니스 로직이 들어가는 Service 빈을 등록한다.
 * Service 어노테이션 내부에 Component 어노테이션이 등록되어 있어 해당 클래스를 빈으로 등록한다.
 * 빈 등록시 빈의 이름은 따로 명시하지 않을 경우 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 사용하게 된다.
 * ex) className = TestService, beanName = testService
 */
@Service
public class OwnerService {
    /**
     * "Autowired", "Inject", "Resource"
     * 의존성 주입이라는 공통적인 기능을 가지고 있다.
     * "Autowired"는 스프링 프레임워크에서 지원하는 어노테이션으로 객체의 타입을 보고 의존성을 주입한다.
     * "Inject"는 자바에서 지원하는 어노테이션이고 "Autowired"와 같이 타입으로 의존성을 주입한다.
     * "Resource" 또한 자바에서 지원하는 어노테이션이지만 "Inject"와 달리 이름으로 의존성을 주입한다.
     * 만약, 진행 중인 프로젝트가 스프링에 종속적이지 않다면 "Inject"와 "Resource" 중 선택하면 되겠다.
     */
    @Autowired
    OwnerMapper ownerMapper;

    public Owner getOwnerFromId(Long id) {
        return ownerMapper.getOwnerFromId(id);
    }

    public Owner getOwnerFromEmail(String email) {
        return ownerMapper.getOwnerFromEmail(email);
    }

    @Transactional
    public void registerOwner(String email, String name, String password) {
        Owner owner = ownerMapper.getOwnerFromEmail(email);

        if (owner != null) {
            throw new IllegalStateException("이미 등록된 정보(사장)입니다.");
        }

        Owner builder = Owner.builder()
                .email(email)
                .name(name)
                .password(SHA256Util.encode(password))
                .build();

        ownerMapper.insertOwner(builder);
    }

    public Owner login(String email, String password) {
        Owner owner = ownerMapper.getOwnerFromEmail(email);

        if (owner == null) {
            throw new IllegalStateException("정보(사장)가 없습니다.");
        }

        if (owner.isNotMatchPassword(SHA256Util.encode(password))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return owner;
    }
}
