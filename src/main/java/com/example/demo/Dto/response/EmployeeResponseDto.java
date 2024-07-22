package com.example.demo.Dto.response;

import java.time.LocalDate;

import com.example.demo.model.Address;
import com.example.demo.model.enums.Designation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeResponseDto  {
	private Long id;
	private String email;
	private String name;
	

	private String contactNo;
	
	private LocalDate dateOfBirth;
	
	
	private Designation designation;
	
	private DepartmentResponseDto department;
	
	
	private Address address;
	
	private String image;
	

	
}
