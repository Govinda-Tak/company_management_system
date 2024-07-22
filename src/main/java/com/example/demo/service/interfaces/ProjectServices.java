package com.example.demo.service.interfaces
;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.request.ProjectRequestDto;
import com.example.demo.Dto.response.AllProjectsWithAllDetailsResponseDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.DetailedProjectResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.ProjectResponseDto;

public interface ProjectServices {
	
	public ResponseEntity<String> addEmployee(Long id,Long empId);
	public ResponseEntity<String> addDepartment(Long id,Long deptId);
	
	public ResponseEntity<ProjectResponseDto> getProject(Long id);
	
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments(Long id);
	
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees(Long id);
	
	public ResponseEntity<ProjectResponseDto> updateProject(Long id,ProjectRequestDto project);
	
	public ResponseEntity<ProjectResponseDto> removeProject(Long id);
	
	public ResponseEntity<ProjectResponseDto> registerProject(ProjectRequestDto newProject);
	
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects();
	
	public ResponseEntity<List<ProjectResponseDto>> getProjectsByStartingDate(LocalDate startingDate);

	public ResponseEntity<DetailedProjectResponseDto> getCompleteDetailsOfProject(Long id);
	public ResponseEntity<AllProjectsWithAllDetailsResponseDto> getCompleteDetailsOfProjects(int page,int size);
}
