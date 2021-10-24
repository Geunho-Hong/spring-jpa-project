package com.jpa.develop.dto.user;

import com.jpa.develop.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserResponseDto {

    private final Long seq;
    private final String userId;
    private final String userName;
    private final String userPhoneNumber;
    private final LocalDate userBirthDate;

    public static UserResponseDto toUserResponse(User user) {
        return UserResponseDto.builder()
                .seq(user.getSeq())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userBirthDate(user.getUserBirthDate())
                .build();
    }

}
