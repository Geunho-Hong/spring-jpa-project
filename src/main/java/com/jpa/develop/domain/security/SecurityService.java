package com.jpa.develop.domain.security;

import antlr.Token;
import com.jpa.develop.config.JwtTokenUtil;
import com.jpa.develop.domain.user.User;
import com.jpa.develop.domain.user.UserRepository;
import com.jpa.develop.domain.user.exception.LoginFailException;
import com.jpa.develop.dto.jwt.TokenResponseDto;
import com.jpa.develop.dto.user.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : SecurityService
 * @Date        : 2021-12-23
 * @author      : user
 * @description :
 * 
 */

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public TokenResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {

        User user = userRepository.findByUserId(userLoginRequestDto.getUserId())
                .orElseThrow(() -> new LoginFailException("해당 유저는 존재하지 않습니다"));

        if(!passwordEncoder.matches(userLoginRequestDto.getUserPw(), user.getUserPw())) {
            throw new LoginFailException("패스워드가 일치하지 않습니다");
        }

        TokenResponseDto tokenResponseDto = jwtTokenUtil.generateJwtToken(user.getUserId(),user.getRoles());

        return tokenResponseDto;
    }



}
