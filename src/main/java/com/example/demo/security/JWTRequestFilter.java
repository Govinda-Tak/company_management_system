package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//custom filter
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils utils;
	@Autowired
	private CustomUserDetailsServiceImpl userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// check for authorization hdr
		String authHeadr = request.getHeader("Authorization");
		if (authHeadr != null && authHeadr.startsWith("Bearer")) {
			System.out.println("got bearer token");
			String token = authHeadr.substring(7);
			System.out.println(token);
			try {			UserDetails user=userService.loadUserByUsername(utils.getUsernameFromToken(token));
			System.out.println(user);
			if( utils.validateToken(token,user))
			// extract subject from the token
			{
		System.out.println("hello....");
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(user, null,
					user.getAuthorities());
			System.out.println(authentication);
			//save above auth object in the spring sec ctx
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("end of filter !!");
			}
			
			else
			{
				throw new BadCredentialsException("Token not valid !!");
			}}catch(Exception e)
			{
				System.out.println("in filter exception !! ::"+e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			
		} else
			System.out.println("req did not contain any bearer token");
		System.out.println("calling to next filter !!!");
		filterChain.doFilter(request, response);// passing the control to the nexyt filter in the chain

	}

}
