package com.example.demo.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.request.ProjectRequestDto;
import com.example.demo.Dto.response.AllProjectsWithAllDetailsResponseDto;
import com.example.demo.Dto.response.DepartmentResponseDto;
import com.example.demo.Dto.response.EmployeeResponseDto;
import com.example.demo.Dto.response.FullProjectResponseDto;
import com.example.demo.Dto.response.ProjectResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
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
	@Autowired
	private TypeMap<Project, FullProjectResponseDto> projectToFullProjectMapper;
	@Autowired
	private EmployeeRepository employeeDao;
	@Autowired
	private DepartmentRepository departmentDao;

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
//	project.getEmployee().forEach(e->project.removeEmployee(e));
		List<Employee>eList=project.getEmployee();
		for(int i=0;i<eList.size();i++)
		{
			project.removeEmployee(eList.get(i));
		}
	List<Department>dList=project.getDepartment();
	for(int j=0;dList.size()>j;j++)
	{
		dList.get(j).removeProject(project);
	}
	//projectdao.deleteById(id);
		return new ResponseEntity<ProjectResponseDto>(map.map(project, ProjectResponseDto.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProjectResponseDto> registerProject(ProjectRequestDto newProject) {
		// TODO Auto-generated method stub
		Project project=new Project(newProject.getName(),newProject.getStartDate(),newProject.getEndDate());
	project=projectdao.save(project);
		return new ResponseEntity<ProjectResponseDto>(map.map(project, ProjectResponseDto.class),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
		// TODO Auto-generated method stub
	List<ProjectResponseDto>pList=	projectdao.findAll().stream().map(project->map.map(project, ProjectResponseDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<ProjectResponseDto>>(pList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProjectResponseDto>> getProjectsByStartingDate(LocalDate startingDate) {
		// TODO Auto-generated method stub
		List<ProjectResponseDto>pList=	projectdao.findAllByStartDate(startingDate).orElseThrow(()->new ResourceNotFoundException("No any Project registered with this starting date ::"+startingDate)).stream().map(project->map.map(project, ProjectResponseDto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<ProjectResponseDto>>(pList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FullProjectResponseDto> getCompleteDetailsOfProject(Long id) {
		// TODO Auto-generated method stub
	Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("No any project registered with this id ::"+id));
	//project.getEmployee().forEach(e->e.getName());
	   FullProjectResponseDto response= projectToFullProjectMapper.map(project);
	   List<EmployeeResponseDto>eList=project.getEmployee().stream().map(emp->map.map(emp, EmployeeResponseDto.class)).collect(Collectors.toList());
	   List<DepartmentResponseDto>dList=project.getDepartment().stream().map(dept->map.map(dept, DepartmentResponseDto.class)).collect(Collectors.toList());
	   response.setDepartment(dList);
	   response.setEmployee(eList);
		return new ResponseEntity<FullProjectResponseDto>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AllProjectsWithAllDetailsResponseDto> getCompleteDetailsOfProjects(int pageNumber,int size) {
		// TODO Auto-generated method stub
		Pageable pageInfo=PageRequest.of(pageNumber, size, Sort.by("startDate").ascending().and(Sort.by("endDate").ascending()));
	Page<Project> page=	projectdao.findAll(pageInfo);
List<FullProjectResponseDto>pList=	page.getContent().stream().map(project->projectToFullProjectMapper.map(project)).collect(Collectors.toList());
// Map departments for each project
List<List<DepartmentResponseDto>> dList = page.getContent().stream()
        .map(project -> project.getDepartment().stream()
                .map(dept -> map.map(dept, DepartmentResponseDto.class))
                .collect(Collectors.toList()))
        .collect(Collectors.toList());

// Map employees for each project
List<List<EmployeeResponseDto>> eList = page.getContent().stream()
        .map(project -> project.getEmployee().stream()
                .map(emp -> map.map(emp, EmployeeResponseDto.class))
                .collect(Collectors.toList()))
        .collect(Collectors.toList());
// Set departments and employees for each project
for (int i = 0; i < pList.size(); i++) {
    FullProjectResponseDto fullProject = pList.get(i);
    fullProject.setDepartment(dList.get(i));
    fullProject.setEmployee(eList.get(i));
}

// Prepare page information
AllProjectsWithAllDetailsResponseDto.PageInformation pageInfoDto = new AllProjectsWithAllDetailsResponseDto.PageInformation();
pageInfoDto.setPageNumber(page.getNumber());
pageInfoDto.setPageSize(page.getSize());
pageInfoDto.setTotalElements((int) page.getTotalElements());
pageInfoDto.setTotalPages(page.getTotalPages());

// Prepare final response
AllProjectsWithAllDetailsResponseDto responseDto = new AllProjectsWithAllDetailsResponseDto();
responseDto.setProjectList(pList);
responseDto.setPageInfo(pageInfoDto);

return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addEmployee(Long id, Long empId) {
		// TODO Auto-generated method stub
		
		Employee emp=employeeDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("No any employee registered with this id::"+empId));
		Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid project id ::"+id));
		project.addEmployee(emp);
		emp.getDepartment().addProject(project);
		return new ResponseEntity<String>("Successfully added !!",HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> addDepartment(Long id, Long deptId) {
		// TODO Auto-generated method stub
		Department dept=departmentDao.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Invalid Department id::"+deptId));
		Project project=projectdao.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid project id ::"+id));
		dept.addProject(project);
		return new ResponseEntity<String>("Successfully added !!",HttpStatus.ACCEPTED);
	}

}
