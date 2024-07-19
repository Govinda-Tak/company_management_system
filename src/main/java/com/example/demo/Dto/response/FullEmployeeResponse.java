package com.example.demo.Dto.response;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Address;
import com.example.demo.model.enums.Designation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"department","project"})
public class FullEmployeeResponse {

	private Long id;
	private String email;
	private String name;
	

	private String contactNo;
	
	private LocalDate dateOfBirth;
	
	
	private Designation designation;
	
	
	
	
	private Address address;
	
	private DepartmentResponseDto department;
	
	private List<ProjectResponseDto> project;
	
}
