package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Owner;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper {
    // 사장 조회(id)
    Owner getOwnerFromId(Long id);

    // 사장 조회(email)
    Owner getOwnerFromEmail(String email);

    // 회원가입
    void insertOwner(Owner owner);

}
