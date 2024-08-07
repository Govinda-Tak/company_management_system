package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.response.SigninResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.GitHubUser;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.util.JwtUtils;

import java.util.Map;

@Service
public class GitHubUserService {
	@Autowired
	private EmployeeRepository employeeDao;
	@Autowired
	private JwtUtils jwt;

    public ResponseEntity<SigninResponse> processOAuthPostLogin(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();

        GitHubUser user = new GitHubUser();
        user.setId((Long) attributes.get("id"));
        user.setLogin((String) attributes.get("login"));
        user.setName((String) attributes.get("name"));
        user.setEmail((String) attributes.get("email"));
        user.setAvatarUrl((String) attributes.get("avatar_url"));

        //verify the email in database
        employeeDao.findByEmail(user.getEmail()).orElseThrow(()->new ResourceNotFoundException("No any employee registered with this email id ::"+user.getEmail()));
        SigninResponse response=new SigninResponse(jwt.generateToken(user.getEmail()),"successfully sign-in !!");
        // Additional processing, such as saving to a database
        // saveUserToDatabase(user);
        
        return new ResponseEntity<SigninResponse>(response, HttpStatus.OK);
    }

    // Additional methods for user handling (e.g., saving to a database)
}
