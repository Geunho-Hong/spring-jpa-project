package com.jpa.develop.user;

import com.jpa.develop.domain.user.User;
import com.jpa.develop.domain.user.UserRepository;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    static EnhancedRandom userCreator;

    @BeforeAll
    static void setUp(){
        userCreator = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(f -> f.getName().equals("userId"), () -> "grayson")
                .randomize(f -> f.getName().equals("userName"), () -> "홍근호")
                .randomize(f -> f.getName().equals("userPhoneNumber"),() -> "010-7122-8159")
                .randomize(f -> f.getName().equals("userBirthDate"),() ->LocalDate.of(1994,01,14))
                .build();
    }

    @Test
    @DisplayName("회원 저장 하기")
    void insertUser(){

        // given
        User user = User.builder()
                .userId("grayson")
                .userPw("8159")
                .userName("홍근호")
                .userPhoneNumber("010-7122-8159")
                .userBirthDate(LocalDate.of(1994,01,14))
                .build();

        // when
        userRepository.save(user);
        testEntityManager.flush();

        User findUser = userRepository.findById(user.getUserNo())
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(findUser.getUserNo()).isNotZero();
        assertThat(findUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(findUser.getUserPhoneNumber()).isEqualTo(user.getUserPhoneNumber());
        assertThat(findUser.getUserBirthDate()).isEqualTo(user.getUserBirthDate());

    }

    @Test
    @DisplayName("회원 아이디 존재 여부 확인 하기")
    void isExistUserId() {

        User user = userCreator.nextObject(User.class);
        userRepository.save(user);

        boolean isExistUserId = userRepository.existsByUserId(user.getUserId());

        assertThat(isExistUserId).isTrue();

    }

    @Test
    @DisplayName("핸드폰 존재 여부 확인 하기")
    void isExistPhoneNumber() {

        User user = userCreator.nextObject(User.class);
        userRepository.save(user);

        boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumber(user.getUserPhoneNumber());

        assertThat(isExistPhoneNumber).isTrue();
    }

}
