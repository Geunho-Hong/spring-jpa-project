package com.jpa.develop.dto.user;

import com.jpa.develop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : UserLoginDto
 * @Date        : 2021-12-23
 * @author      : GeunhoHong
 * @description :
 * 
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {

    private String userId;
    private String userPw;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .userPw(passwordEncoder.encode(userPw))
                .build();
    }

}
