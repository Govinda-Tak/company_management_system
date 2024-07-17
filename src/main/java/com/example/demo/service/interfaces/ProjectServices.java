package com.example.demo.service.interfaces
;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.request.ProjectRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullProjectResponseDto;
import com.example.demo.Dto.response.ProjectResponseDto;

public interface ProjectServices {
	
	public ResponseEntity<ProjectResponseDto> getProject(Long id);
	
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments(Long id);
	
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees(Long id);
	
	public ResponseEntity<ProjectResponseDto> updateProject(Long id,ProjectRequestDto project);
	
	public ResponseEntity<ProjectResponseDto> removeProject(Long id);
	
	public ResponseEntity<ProjectResponseDto> registerProject(ProjectRequestDto newProject);
	
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects();
	
	public ResponseEntity<List<ProjectResponseDto>> getProjectsByStartingDateAndEndingDate(LocalDate startingDate,LocalDate endingDate);

	public ResponseEntity<FullProjectResponseDto> getCompleteDetailsOfProject(Long id);
}
