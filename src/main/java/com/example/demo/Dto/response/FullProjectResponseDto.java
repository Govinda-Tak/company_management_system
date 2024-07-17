package com.example.demo.Dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"department","employee"})
public class FullProjectResponseDto {

	private Long id;
	private String name;
	
	
	private LocalDate startDate;
	
	
	private LocalDate endDate;
	
	private List<DepartmentResponseDto> department;
	
	private List<EmployeeResponseDto> employee;
}
