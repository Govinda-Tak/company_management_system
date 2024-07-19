package com.example.demo.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.request.EmployeeRequestDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullEmployeeResponse;
import com.example.demo.Dto.response.ProjectResponseDto;

public interface EmployeeService {
	
	public ResponseEntity<EmployeeResponseDto> getEmployee(Long id);
	
	public ResponseEntity<EmployeeResponseDto> updateEmployee(Long id,EmployeeRequestDto employee);
	
	public ResponseEntity<EmployeeResponseDto> removeEmployee(Long id);
	
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects(Long id);
	
	public ResponseEntity<EmployeeResponseDto> addEmployee(EmployeeRequestDto newEmployee);
	
	public ResponseEntity<EmployeeResponseDto> TransferEmployee(Long empId,Long deptId);
	
	public ResponseEntity<FullEmployeeResponse> getCompleteDetailsOfEmployee(Long id);

}
