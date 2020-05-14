package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.Owner;
import com.hoon.foodrocket.mapper.OwnerMapper;
import com.hoon.foodrocket.util.SHA256Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {
    private String email = "test@example.com";
    private String password = "1234";

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerMapper ownerMapper;

    @Test
    public void 회원가입_성공() throws Exception {
        // given
        given(ownerMapper.getOwnerFromEmail(email)).willReturn(null);

        // when
        ownerService.registerOwner(email, "test", password);

        // then
        verify(ownerMapper).insertOwner(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 회원가입_실패_중복회원() throws Exception {
        // given
        Owner owner = generateOwner();
        given(ownerMapper.getOwnerFromEmail(email)).willReturn(owner);

        // when
        ownerService.registerOwner(email, "test", password);

        // then
        verify(ownerMapper, never()).insertOwner(any());
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 로그인_성공() throws Exception {
        // given
        Owner owner = generateOwner();
        given(ownerMapper.getOwnerFromEmail(email)).willReturn(owner);

        // when
        Owner login = ownerService.login(email, password);

        // then
        assertEquals(owner.getEmail(), login.getEmail());
    }

    @Test(expected = IllegalStateException.class)
    public void 로그인_실패_이메일_불일치() throws Exception {
        // given
        given(ownerMapper.getOwnerFromEmail(email)).willReturn(null);

        // when
        ownerService.login(email, password);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 로그인_실패_비밀번호_불일치() throws Exception {
        // given
        String failPassword = "xxxx";

        Owner owner = generateOwner();
        given(ownerMapper.getOwnerFromEmail(email)).willReturn(owner);

        // when
        ownerService.login(email, failPassword);

        // then
        fail("예외가 발생해야 한다.");
    }

    private Owner generateOwner() {
        return Owner.builder()
                .email("test@example.com")
                .name("tester")
                .password(SHA256Util.encode("1234"))
                .build();
    }
}