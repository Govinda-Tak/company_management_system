package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.request.EmployeeRequestDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullEmployeeResponse;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.service.interfaces.EmployeeService;

import jakarta.validation.Valid;
@RequestMapping("/api/employee")
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable long id)
	{
		return empService.getEmployee(id);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<EmployeeResponseDto> registerEmployee(@ModelAttribute @Valid EmployeeRequestDto newEmployee)
	{
		return empService.addEmployee(newEmployee);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> removeEmployee(@PathVariable long id)
	{
		return empService.removeEmployee(id);
	}
	@PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@ModelAttribute @Valid EmployeeRequestDto employee,@PathVariable long id)
	{
		
		return empService.updateEmployee(id, employee);
	}
	@GetMapping("/all-projects/{id}")
	public ResponseEntity<List<ProjectResponseDto>> getAllProjectsOfEmployee(@PathVariable long id)
	{
		
		return empService.getAllProjects(id);
	}
	@GetMapping("/all-detaills/{id}")
	public ResponseEntity<FullEmployeeResponse> getAllDetailsOfEmployee(@PathVariable long id)
	{
		
		return empService.getCompleteDetailsOfEmployee(id);
	}
	@PutMapping("/transfer-employee/{id}")
	public ResponseEntity<EmployeeResponseDto> transferEmployee(@PathVariable("id") long id,@RequestParam(value = "deptId",required = true) long deptId )
	{
		
		return empService.TransferEmployee(id,deptId);
	}
}
