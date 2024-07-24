package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private EmployeeRepository employeeDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee=employeeDao.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("no user registered with this email id ::"+username));
		List<GrantedAuthority>authorities=new ArrayList<>();
		return null;
	}

}
