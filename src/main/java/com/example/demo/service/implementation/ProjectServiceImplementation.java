package com.example.demo.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.request.ProjectRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullProjectResponseDto;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.interfaces.ProjectServices;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class ProjectServiceImplementation implements ProjectServices {
	@Autowired
	private ProjectRepository projectdao;
	@Autowired
	private ModelMapper map;

	@Override
	public ResponseEntity<ProjectResponseDto> getProject(Long id) {
		// TODO Auto-generated method stub
		
		return new ResponseEntity<ProjectResponseDto>(map.map(projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this id ::"+id)),ProjectResponseDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<DepartmentResponseDto>> getDepartments(Long id) {
		// TODO Auto-generated method stub
		List<DepartmentResponseDto>dList=new ArrayList<>();
		Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this id ::"+id));
		project.getDepartment().forEach(d->dList.add(map.map(d, DepartmentResponseDto.class)));
		return new ResponseEntity<List<DepartmentResponseDto>>(dList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees(Long id) {
		// TODO Auto-generated method stub
		List<EmployeeResponseDto>eList=new ArrayList<>();
		Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this id ::"+id));
		project.getEmployee().forEach(e->eList.add(map.map(e, EmployeeResponseDto.class)));
		return new ResponseEntity<List<EmployeeResponseDto>>(eList,HttpStatus.OK);
	
	
	}

	@Override
	public ResponseEntity<ProjectResponseDto> updateProject(Long id, ProjectRequestDto project) {
		// TODO Auto-generated method stub
		Project p=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this id ::"+id));
		map.map(project, p);
		return new ResponseEntity<ProjectResponseDto>(map.map(p, ProjectResponseDto.class),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<ProjectResponseDto> removeProject(Long id) {
		// TODO Auto-generated method stub
		Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this id ::"+id));
	project.getEmployee().forEach(e->project.removeEmployee(e));
	project.getDepartment().forEach(d->d.removeProject(project));
	projectdao.deleteById(id);
		return new ResponseEntity<ProjectResponseDto>(map.map(project, ProjectResponseDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProjectResponseDto> registerProject(ProjectRequestDto newProject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getProjectsByStartingDateAndEndingDate(LocalDate startingDate,
			LocalDate endingDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<FullProjectResponseDto> getCompleteDetailsOfProject(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
