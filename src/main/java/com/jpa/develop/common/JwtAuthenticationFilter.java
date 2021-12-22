package com.jpa.develop.common;

import com.jpa.develop.config.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : JwtAuthenticationFilter
 * @Date        : 2021-12-22
 * @author      : GeunhoHong
 * @description :
 * 
 */

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenUtil jwtTokenUtil;


    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     * @author : GeunhoHong
     * @description : 헤더에서 jwt-token을 받아 와 유효한 토큰인지 확인 후
     *                FilterChain에 추가
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response
            , FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenUtil.resolveToken((HttpServletRequest) request);

        if(token != null && jwtTokenUtil.validationToken(token)) {
            Authentication authentication = jwtTokenUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
