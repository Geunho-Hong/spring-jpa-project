package com.jpa.develop.dto.user;

import com.jpa.develop.domain.user.User;
import com.jpa.develop.validator.BirthDate;
import com.jpa.develop.validator.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {

    private Long seq;

    @NotEmpty(message = "유저 아이디를 입력해 주세요")
    @Size(min = 2, max = 10)
    private String userId;

    @NotEmpty(message = "유저 패스워드를 입력해 주세요")
    private String userPw;

    @NotEmpty(message = "이름을 입력해주세요")
    @Size(min = 2, max = 10)
    private String userName;

    @PhoneNumber
    private String userPhoneNumber;

    @BirthDate
    private LocalDate userBirthDate;

    // 최초 가입시 roles는 ROLE_USER 로 설정
    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .userBirthDate(userBirthDate)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }


}
