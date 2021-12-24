package com.jpa.develop.dto.jwt;

import lombok.*;

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
@ToString
public class TokenResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireDate;

}
