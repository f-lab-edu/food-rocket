package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.mapper.UserMapper;
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

/**
 * Mockito 를 사용하기 위해 적용한다.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private String email = "test@example.com";
    private String password = "1234";

    /**
     * '@Mock' 어노테이션으로 만들어진 가짜 객체를 주입할 수 있다.
     */
    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    public void 회원가입_성공() throws Exception {
        // given
        User user = generateUser();

        // when
        userService.registerUser(user);

        // then
        verify(userMapper).registerUser(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원_예외발생() throws Exception {
        // given
        User user = generateUser();

        given(userMapper.getUserFromEmail(email)).willReturn(user);

        // when
        userService.registerUser(user);

        // then
        verify(userMapper, never()).registerUser(any());
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 로그인_성공() throws Exception {
        // given
        User user = generateUser();

        given(userMapper.getUserFromEmail(email)).willReturn(user);

        // when
        User login = userService.login(email, password);

        // then
        assertEquals(login.getEmail(), email);
    }

    @Test(expected = IllegalStateException.class)
    public void 이메일_불일치() throws Exception {
        // given
        String tmpEmail = "null@example.com";

        given(userMapper.getUserFromEmail(tmpEmail)).willReturn(null);

        // when
        userService.login(tmpEmail, password);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 비밀번호_불일치() throws Exception {
        // given
        String tmpPassword = "x";

        User user = generateUser();
        given(userMapper.getUserFromEmail(email)).willReturn(user);

        // when
        userService.login(email, tmpPassword);

        // then
        fail("예외가 발생해야 한다.");
    }

    private User generateUser() {
        return User.builder()
                .email(email)
                .name("test")
                .password(SHA256Util.encode(password))
                .address("test")
                .region("test")
                .build();
    }
}