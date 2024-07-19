package com.example.demo.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.request.EmployeeRequestDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullEmployeeResponse;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.interfaces.EmployeeService;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class EmployeeServiceimplementation implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeDao;
	@Autowired
	private DepartmentRepository departmentDao;
	@Autowired
	private ModelMapper map;

	@Override
	public ResponseEntity<EmployeeResponseDto> getEmployee(Long id) {
		// TODO Auto-generated method stub
return new ResponseEntity<EmployeeResponseDto>(map.map(employeeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+id)),EmployeeResponseDto.class),HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<EmployeeResponseDto> updateEmployee(Long id, EmployeeRequestDto employee) {
		// TODO Auto-generated method stub
		Employee e=employeeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+id));
		map.map(employee, e);
		return new ResponseEntity<EmployeeResponseDto>(map.map(e, EmployeeResponseDto.class),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<EmployeeResponseDto> removeEmployee(Long id) {
		// TODO Auto-generated method stub
	Employee emp=employeeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+id));
	emp.getDepartment().removeEmployee(emp);
		return new ResponseEntity<EmployeeResponseDto>(map.map(emp, EmployeeResponseDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects(Long id) {
		// TODO Auto-generated method stub
		Employee emp=employeeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+id));
		List<ProjectResponseDto> pList=new ArrayList<>();
		emp.getProject().forEach(p->pList.add(map.map(p, ProjectResponseDto.class)));
		return new ResponseEntity<List<ProjectResponseDto>>(pList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmployeeResponseDto> addEmployee(EmployeeRequestDto newEmployee) {
		// TODO Auto-generated method stub
		Department dept=departmentDao.findById(newEmployee.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("No any Department registered with this department id ::"+newEmployee.getDepartmentId()));
	
		//Employee emp=map.map(newEmployee, Employee.class);
		Address address=new Address(newEmployee.getAddress(),newEmployee.getState(),newEmployee.getCountry(),newEmployee.getPinCode());
		Employee emp=new Employee(newEmployee.getName(),newEmployee.getContactNo(),newEmployee.getDateOfBirth(),newEmployee.getDesignation(),newEmployee.getEmail(),newEmployee.getPassword(),address);
		System.out.println(emp+" ... in emp service create !!");
		dept.addEmployee(emp);
		employeeDao.save(emp);
		return new ResponseEntity<EmployeeResponseDto>(map.map(emp,EmployeeResponseDto.class),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<EmployeeResponseDto> TransferEmployee(Long empId, Long deptId) {
		// TODO Auto-generated method stub
	Department newDept=departmentDao.findById(deptId).orElseThrow(()-> new ResourceNotFoundException("Department not found for this id :: " + deptId));	
		Employee emp=employeeDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+empId));
	emp.getDepartment().removeEmployee(emp);
	newDept.addEmployee(emp);
		return new ResponseEntity<EmployeeResponseDto>(map.map(emp, EmployeeResponseDto.class),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<FullEmployeeResponse> getCompleteDetailsOfEmployee(Long id) {
		// TODO Auto-generated method stub
	Employee emp=employeeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No Any Employee registered with this id :: "+id));
	FullEmployeeResponse fEmp=new FullEmployeeResponse();
	fEmp.setAddress(emp.getAddress());
	fEmp.setContactNo(emp.getContactNo());
	fEmp.setDateOfBirth(emp.getDateOfBirth());
	fEmp.setDepartment(map.map(fEmp.getDepartment(),DepartmentResponseDto.class));
	fEmp.setDesignation(emp.getDesignation());
	fEmp.setEmail(emp.getEmail());
	fEmp.setId(emp.getId());
	fEmp.setName(emp.getName());

	List<ProjectResponseDto>pList=new ArrayList<>();
	emp.getProject().forEach(p->pList.add(map.map(p, ProjectResponseDto.class)));
	fEmp.setProject(pList);
		return new ResponseEntity<FullEmployeeResponse>(fEmp,HttpStatus.OK);
	}

}
