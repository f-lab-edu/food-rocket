package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.Owner;
import com.hoon.foodrocket.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {
    @Autowired
    OwnerMapper ownerMapper;

    @Transactional
    public Owner getOwnerFromId(Long id) {
        return ownerMapper.getOwnerFromId(id);
    }

    @Transactional
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
                .password(password)
                .build();

        ownerMapper.registerOwner(builder);
    }

    @Transactional
    public Owner login(String email, String password) {
        Owner owner = ownerMapper.getOwnerFromEmail(email);

        if (owner == null) {
            throw new IllegalStateException("정보(사장)가 없습니다.");
        }

        if (!owner.isMatchPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return owner;
    }
}
