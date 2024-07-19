package com.example.demo.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.internal.bytebuddy.description.type.TypeVariableToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.request.CreateDepartmentWithEmployeesAndProjectsRequestDto;
import com.example.demo.Dto.request.DepartmentRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullDepartmentResponse;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.interfaces.DepartmentService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class DepartmentServiceImplement implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentDao;
	@Autowired
	private ModelMapper map;
	@Autowired
	private TypeMap<Department,FullDepartmentResponse> departmentToFullDepartmentResponse;
	@Autowired
	private TypeMap<DepartmentRequestDto, Department> departmentRequestDtoToDepartmentMap;

	@Override
	public ResponseEntity<DepartmentResponseDto> getDepartment(long id) {
		// TODO Auto-generated method stub
		  Department d = departmentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
	        DepartmentResponseDto res = map.map(d, DepartmentResponseDto.class);
	        return new ResponseEntity<>(res, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<FullDepartmentResponse> getAllDetailsOfDepartment(long id) {
		// TODO Auto-generated method stub
		  Department d = departmentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
		  	List<ProjectResponseDto>pList=new ArrayList<>();
		  	List<EmployeeResponseDto>eList=new ArrayList<>();
		 d.getProject().forEach(p->pList.add(map.map(p, ProjectResponseDto.class)));
		 d.getEmployee().forEach(e->eList.add(map.map(e,EmployeeResponseDto.class)));
		 FullDepartmentResponse dept=departmentToFullDepartmentResponse.map(d);
	   dept.setProject(pList);
	   dept.setEmployee(eList);
		 
		return new ResponseEntity<>(dept,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<DepartmentResponseDto>> getListOfDepartment() {
		// TODO Auto-generated method stub
		List<DepartmentResponseDto>dList=new ArrayList<>();
		departmentDao.findAll().forEach(d->dList.add(map.map(d,DepartmentResponseDto.class)));
		return new ResponseEntity<List<DepartmentResponseDto>>(dList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartmentResponseDto> removeDepartment(long id) {
		// TODO Auto-generated method stub
		Department d=departmentDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found for this id :: " + id));
	DepartmentResponseDto dept=map.map(d,DepartmentResponseDto.class);	
	d.getEmployee().forEach(e->d.removeEmployee(e));
	d.getProject().forEach(p->d.removeProject(p));
	
	departmentDao.deleteById(id);
		return new ResponseEntity<DepartmentResponseDto>(dept,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartmentResponseDto> updateDepartment(long id, DepartmentRequestDto department) {
		// TODO Auto-generated method stub
		  Department d = departmentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
		//  d=map.map
		d.setName(department.getName());
		d.setUpdatedAt(LocalDateTime.now());
		  return new ResponseEntity<DepartmentResponseDto>(map.map(d, DepartmentResponseDto.class),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getProjects(long id) {
		// TODO Auto-generated method stub
		  Department d = departmentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
			
		  List<ProjectResponseDto>pList=new ArrayList<>();
		  d.getProject().forEach(p->pList.add(map.map(p, ProjectResponseDto.class)));
		return new ResponseEntity<List<ProjectResponseDto>>(pList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepartmentResponseDto> createDepartment(DepartmentRequestDto newDepartment) {
		// TODO Auto-generated method stub
		Department dept=new Department(newDepartment.getName());
		dept.setCreatedAt(LocalDateTime.now());
		dept.setUpdatedAt(LocalDateTime.now());
		return  new ResponseEntity<DepartmentResponseDto>(map.map(departmentDao.save(dept),DepartmentResponseDto.class ),HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<FullDepartmentResponse> createDepartmentWithEmployeesAndProjects(
			CreateDepartmentWithEmployeesAndProjectsRequestDto newDepartment) {
		// TODO Auto-generated method stub
		return null;
//		Department dept=new Department(newDepartment.getName());
//		newDepartment.getEmployee().forEach(e->dept.addEmployee(map.map(e, Employee.class)));
//		newDepartment.getProject().forEach(p->dept.addProject(map.map(p, Project.class)));
//		dept=departmentDao.save(dept);
//		FullDepartmentResponse response=departmentToFullDepartmentResponse.map(dept);
//		
//		List<ProjectResponseDto>pList=new ArrayList<>();
//	  	List<EmployeeResponseDto>eList=new ArrayList<>();
//	 dept.getProject().forEach(p->pList.add(map.map(p, ProjectResponseDto.class)));
//	 dept.getEmployee().forEach(e->eList.add(map.map(e,EmployeeResponseDto.class)));
//	 
//	 response.setProject(pList);
//	   response.setEmployee(eList);
//		return new ResponseEntity<FullDepartmentResponse>(response,HttpStatus.CREATED);
	}

}
