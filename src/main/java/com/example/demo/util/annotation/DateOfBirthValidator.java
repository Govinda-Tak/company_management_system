package com.example.demo.util.annotation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {

	@Override
	public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		if(dob==null)
		{
			return true;//for null checking use notNull annotation.
		}
		int year=Period.between(dob, LocalDate.now()).getYears();
		
		return year>=18 &&year<=60 ?true : false;
	}

}
