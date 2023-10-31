package com.fx21044.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fx21044.entity.User;
import com.fx21044.service.JwtService;
import com.fx21044.service.UserService;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = getJwtFromRequest(request);
			if(jwt != null && jwtService.validateJwtToker(jwt)) {
				String[] decodeJwt = jwtService.getEmailFromJwtToken(jwt).split(",");
				int id = Integer.parseInt(decodeJwt[0]);
				String email = decodeJwt[1];
				
				UserDetails userDetails = userService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        return authHeader.replace("Bearer ", "");
	    }

	   return null;
	}
	
//	private User getUserDetails(String token) {
//        User userDetails = new User();
//        String[] jwtSubject = jwtService.getSubject(token).split(",");
// 
//        userDetails.setId(Integer.parseInt(jwtSubject[0]));
//        userDetails.setEmail(jwtSubject[1]);
// 
//        return userDetails;
//    }
}
