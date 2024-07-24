package com.example.demo.security;



import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.example.demo.model.Employee;

public class CustomUserDetails implements UserDetails {
	private Employee emp;
	

	public CustomUserDetails(Employee user) {
		super();
		this.emp = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Can I return List<SimpleGrantedAuthority> ? YESS
		return List.of(new SimpleGrantedAuthority(emp.getDesignation().name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emp.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
