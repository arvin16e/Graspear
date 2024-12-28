package com.Projectmanagement.config;
/*
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Projectmanagement.Service.BlackListToken;
import com.Projectmanagement.Service.JwtService;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private BlackListToken blacklistService;
	
	static String[] arr= {"/controller/registerUser","/controller/userLogin"};
	 private static final List<String> excludedUrls = Arrays.asList(arr );

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 System.err.println("doFilterInternal enter");
		 System.out.println("Incoming request: " + request.getRequestURI());
		 
		 if (excludedUrls.contains(request.getRequestURI())) {
	            // Allow the request to proceed without JWT authentication
			 System.err.println("Excluded");
	            filterChain.doFilter(request, response);
	            return; // Important to stop further processing here
	        }
		String authHeader=request.getHeader("Authorization").trim();
		String token="";
		String username=null;
		System.err.println(authHeader);
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username=jwtService.extractUserName(token);
			System.err.println("JWTFIlter substring");
		}
		 // Check if token is blacklisted
        if (blacklistService.isTokenBlacklisted(token)) {
            // Token is blacklisted, reject the request
        	System.err.println("JWTFIlter blacklist");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
		
		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails =  applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);
	            System.err.println("Visited MyUserDetailsService");
	            if (jwtService.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource()
	                        .buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	                System.err.println("doFilterInternal");
	            }else {
	            	System.err.println("jwt filter else");
	            }
	        }

	        filterChain.doFilter(request, response);
	}

} */
