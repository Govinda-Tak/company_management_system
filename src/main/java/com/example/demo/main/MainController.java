package com.example.demo.main;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/app")
public class MainController {
	@GetMapping("/")
	public ResponseEntity<String> home()
	{
		return new ResponseEntity<String>("Hello Demo here !!",HttpStatusCode.valueOf(200));
	}
}
