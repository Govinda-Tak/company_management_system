package com.example.demo.Dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"employee","project"})
public class FullDepartmentResponse {
	
private Long id;
	
	private String name;

	private List<EmployeeResponseDto> employee;
	
	private List<ProjectResponseDto> project;

}
