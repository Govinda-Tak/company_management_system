package com.example.demo.Dto.request;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentRequestDto  {
	
	@jakarta.validation.constraints.NotBlank(message = "Department name required !!")
	@Length(min = 2,max = 200,message = "department length should be >=2 and <= 200 character !!")
	private String name;
	
}
