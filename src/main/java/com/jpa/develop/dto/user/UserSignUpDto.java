package com.jpa.develop.dto.user;

import com.jpa.develop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {

    private Long seq;

    @NotEmpty(message = "유저 아이디를 입력해 주세요")
    @Size(min = 5, max = 10)
    private String userId;

    @NotEmpty(message = "유저 패스워드를 입력해 주세요")
    private String userPw;

    @NotEmpty(message = "이름을 입력해주세요")
    @Size(min = 2, max = 10)
    private String userName;

    private String userPhoneNumber;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .build();
    }

}
