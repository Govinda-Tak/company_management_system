package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary CloudinaryConfig()
	{
		return new Cloudinary(ObjectUtils.asMap("cloud_name","dp7xe20u7","api_key","715581187799447","api_secret","WDh6Z48KZer_Kyd-JTgOg9Cg4gc"));
	}

}
