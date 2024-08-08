package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.request.DepartmentRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.FullDepartmentResponse;
import com.example.demo.service.interfaces.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/{id}")
	private ResponseEntity<DepartmentResponseDto> getDepartment(@PathVariable int id)
	{
		return departmentService.getDepartment(id);
	}
	@GetMapping("/full-detail/{id}")
	private ResponseEntity<FullDepartmentResponse> getCompleteDetailsOfDepartment(@PathVariable int id)
	{
		return departmentService.getAllDetailsOfDepartment(id);
	}
	@PreAuthorize("hasRole(`ADMIN`)")
	@PostMapping
	private ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody @Valid DepartmentRequestDto newDepartment)
	{
		return departmentService.createDepartment(newDepartment);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<DepartmentResponseDto> updateDepartment(@RequestBody @Valid DepartmentRequestDto department,@PathVariable int id)
	{
		return departmentService.updateDepartment(id, department);
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<DepartmentResponseDto> removeDepartment(@PathVariable int id)
	{
		return departmentService.removeDepartment(id);
	}
	
	
	
}
