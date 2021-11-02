package com.jpa.develop.user;


import com.jpa.develop.domain.user.User;
import com.jpa.develop.domain.user.UserRepository;
import com.jpa.develop.domain.user.UserService;
import com.jpa.develop.domain.user.exception.PhoneNumberDuplicateException;
import com.jpa.develop.domain.user.exception.UserIdDuplicateException;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    /**
     * given (준비) - when(실행) - then(검증)
     */

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    static EnhancedRandom userRandomObject;

    @BeforeAll
    static void setUp(){
        userRandomObject = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .excludeField(f -> f.getName().equals("userNo"))
                .excludeField(f -> f.getName().equals("regDate"))
                .excludeField(f -> f.getName().equals("modifiedDate"))
                .randomize(f -> f.getName().equals("userId"),() -> "test")
                .build();
    }

    @Test
    @DisplayName("회원 가입")
    void insertUserTest(){

        User user = userRandomObject.nextObject(User.class);

        given(userRepository.existsByUserId(user.getUserId())).willReturn(false);
        given(userRepository.existsByUserPhoneNumber(user.getUserPhoneNumber())).willReturn(false);

        userService.insertUser(user);
    }

    @Test
    @DisplayName("아이디 중복 검사")
    void existByUserId(){

        // given
        User user = userRandomObject.nextObject(User.class);

        given(userRepository.existsByUserId(user.getUserId())).willReturn(true);

        // then
        assertThatThrownBy(() -> userService.insertUser(user))
                .isInstanceOf(UserIdDuplicateException.class);
    }

    @Test
    @DisplayName("핸드폰 중복 검사")
    void existByPhoneNumber(){

        // given
        User user = userRandomObject.nextObject(User.class);

        given(userRepository.existsByUserPhoneNumber(user.getUserPhoneNumber())).willReturn(true);

        // then
        assertThatThrownBy(() -> userService.insertUser(user))
                .isInstanceOf(PhoneNumberDuplicateException.class);

    }


}
