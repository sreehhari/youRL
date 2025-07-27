package com.jerrycan.yourl.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //get JWT from header
            String jwt = jwtTokenProvider.getJwtFromHeader(request);
            if(jwt != null && jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.getUserNameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails!=null){
                    String username =  jwtTokenProvider.getUserNameFromToken(jwt)
                            .
                }

            }
            //validate token
            //if valid get user details
            // getUsername -> load user -> set auth context
        }catch (Exception e) {

        }
    }
}
