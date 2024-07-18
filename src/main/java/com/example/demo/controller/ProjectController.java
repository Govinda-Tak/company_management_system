package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dto.request.ProjectRequestDto;
import com.example.demo.Dto.response.AllProjectsWithAllDetailsResponseDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullProjectResponseDto;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.service.interfaces.ProjectServices;

import jakarta.validation.Valid;
@RequestMapping("/api/project")
@Controller
public class ProjectController {
	@Autowired
	private ProjectServices projectServices;

	@GetMapping("/{id}")
public ResponseEntity<ProjectResponseDto> getProject(@PathVariable long id)
{
		return projectServices.getProject(id);
}
	@GetMapping("/{id}/departments")
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments(@PathVariable("id") long id)
	{
		return projectServices.getDepartments(id);
	}
	
	@GetMapping("/{id}/employees")
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees(@PathVariable("id") long id)
	{
		return projectServices.getEmployees(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable("id") long id,@RequestBody ProjectRequestDto project)
	{
		return projectServices.updateProject(id, project);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectResponseDto> removeProject(@PathVariable("id") long id)
	{
		return projectServices.removeProject(id);
	}
	@PostMapping
	public ResponseEntity<ProjectResponseDto> registerProject(@RequestBody @Valid ProjectRequestDto newProject)
	{
		return projectServices.registerProject(newProject);
	}
	@GetMapping("/all-projects")
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects()
	{
		return projectServices.getAllProjects();
	}
	@GetMapping("/by-starting-date")
	public ResponseEntity<List<ProjectResponseDto>> getProjectsByStartingDate(@RequestParam(value = "startingDate",required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startingDate)
	{
	
		return projectServices.getProjectsByStartingDate(startingDate);
	}
	@GetMapping("/complete-details/{id}")
	public ResponseEntity<FullProjectResponseDto> getCompleteDetailsOfProject(@PathVariable("id") long id)
	{
		return projectServices.getCompleteDetailsOfProject(id);
	}
	@GetMapping("/all-projects/complete-details")
	public ResponseEntity<AllProjectsWithAllDetailsResponseDto> getCompleteDetailsOfProjects(@RequestParam(value = "page",required = true) int page,@RequestParam(value = "size",required = true) int size)
	{
		return projectServices.getCompleteDetailsOfProjects(page, size);
	}
	@PutMapping("/add-employee/{id}")
	public ResponseEntity<String> addEmployee(@PathVariable("id") long id,@RequestParam(value = "empId",required = true) long empId)
	{
		return projectServices.addEmployee(id, empId);
	}
	@PutMapping("/add-department/{id}")
	public ResponseEntity<String> addDepartment(@PathVariable("id") long id,@RequestParam(value = "deptId",required = true) long deptId)
	{
		return projectServices.addDepartment(id, deptId);
	}

}
