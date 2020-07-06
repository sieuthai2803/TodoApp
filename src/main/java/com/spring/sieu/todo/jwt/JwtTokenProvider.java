package com.spring.sieu.todo.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spring.sieu.todo.model.AccountDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private final String JWT_CODE = "LOAD";

    private final long JWT_EXPIRATION = 604800000L;
    
    private final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class.getName());
    
    public String generateToken(AccountDetail accountDetail) {
    	Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        
        return Jwts.builder()
                .setSubject(Long.toString(accountDetail.getAccount().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_CODE)
                .compact();
    }
    
    public Integer getAccountIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_CODE)
                            .parseClaimsJws(token)
                            .getBody();

        return Integer.parseInt(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_CODE).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
