package com.tensquare.common.filter;

import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);//去掉Bearer
            Claims claims =  jwtUtil.parseJWT(token);
            if (claims != null) {
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims",claims);
                }
                if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims",claims);
                }
            }
        }
        return true;
    }
}
