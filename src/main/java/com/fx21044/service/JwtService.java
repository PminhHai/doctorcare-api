package com.fx21044.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@Service
public class JwtService {
	private static final String SECRET_KEY = "12345678912345678912346789123465791235464898944645656456478998123132311323212313213456789789";
    private static final long EXPIRE_TIME = 86400000000L;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());
    
    public String generateTokenLogin(Authentication authentication) {
    	UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
    	
    	return Jwts.builder()
    			.setSubject(String.format("%s,%s", userDetailsImpl.getId(), userDetailsImpl.getEmail()))
    			.setIssuedAt(new Date())
    			.setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
    			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
    			.compact();
    }
    
    public boolean validateJwtToker(String authToken) {
    	try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

    	return false;
    }
    
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
    
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String getEmailFromJwtToken(String token) {
    	
    	String email = Jwts.parser()
    			.setSigningKey(SECRET_KEY)
    			.parseClaimsJws(token)
    			.getBody().getSubject();
    	return email;
    }
}
