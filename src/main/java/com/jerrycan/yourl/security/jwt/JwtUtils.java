package com.jerrycan.yourl.security.jwt;

import com.jerrycan.yourl.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtils {
    //format for jwt token = Authorization -> Bearer -> token
    public String getJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public String generateToken(UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        String userRole = userDetails.getAuthorities().stream()
                .map(authority->authority.getAuthority())
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(username)
                .claim("roles",userRole)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 48))
                .signWith()
                .compact();
    }
}
