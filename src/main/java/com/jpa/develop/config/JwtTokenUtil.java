package com.jpa.develop.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;


/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : JwtTokenUtil
 * @Date        : 2021-12-20
 * @author      : user
 * @description :
 * 
 */

// reference : https://webfirewood.tistory.com/115

@Component
@RequiredArgsConstructor
public class JwtTokenUtil  {

    @Value("${jwt.key}")
    private String SECRET_KEY;

    @Value("${jwt.validity}")
    private String JWT_TOKEN_VALIDITY;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void encodingSecretKey() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * Jwt Token 생성하기
     * @param uniqueUserPk
     * @param roles
     * @return
     */

    public String generateJwtToken(String uniqueUserPk, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(uniqueUserPk);
        claims.put("roles" , roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    /**
     * jwt token에서 인증 정보 조회 하기
     * @param token
     * @return
     */

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUniqueUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    /**
     * Jwt Token에서 회원 정보 가져오기
     * @param token
     * @return
     */

    public String getUniqueUserPk(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    /**
     * Http Request의 Header에서 Token 값 가져오기
     * @param request
     * @return
     */

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }


    /**
     * jwt 토큰의 유효성 및 만료일자 확인
     * @param token
     * @return
     */

    public boolean validationToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

}
