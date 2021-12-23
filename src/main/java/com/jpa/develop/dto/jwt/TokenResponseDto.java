package com.jpa.develop.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : TokenResponseDTO
 * @Date        : 2021-12-23
 * @author      : user
 * @description :
 * 
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long tokenExpireDate;

}
