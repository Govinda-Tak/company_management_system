package com.example.demo.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.request.CreateDepartmentWithEmployeesAndProjectsRequestDto;
import com.example.demo.Dto.request.DepartmentRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.FullDepartmentResponse;
import com.example.demo.Dto.response.ProjectResponseDto;

public interface DepartmentService {
	
	public ResponseEntity<DepartmentResponseDto> getDepartment(long id);
	public ResponseEntity<FullDepartmentResponse> getAllDetailsOfDepartment(long id);
	
	public ResponseEntity<List<DepartmentResponseDto>> getListOfDepartment();
	
	public ResponseEntity<DepartmentResponseDto> removeDepartment(long id);
	
	public ResponseEntity<DepartmentResponseDto> updateDepartment(long id,DepartmentRequestDto department);
	
	public ResponseEntity<List<ProjectResponseDto>> getProjects(long id);
	
	public ResponseEntity<DepartmentResponseDto> createDepartment(DepartmentRequestDto newDepartment);
	
	public ResponseEntity<FullDepartmentResponse> createDepartmentWithEmployeesAndProjects(CreateDepartmentWithEmployeesAndProjectsRequestDto newDepartment);
	

}
