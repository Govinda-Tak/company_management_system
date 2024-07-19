package com.example.demo.Dto.request;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"employee","project"})
public class CreateDepartmentWithEmployeesAndProjectsRequestDto {

	@jakarta.validation.constraints.NotBlank(message = "Department name required !!")
	@Length(min = 2,max = 200,message = "department length should be >=2 and <= 200 character !!")
	private String name;
	
	@Valid
	private List<EmployeeRequestDto> employee;
	@Valid
	private List<ProjectRequestDto> project;
}
