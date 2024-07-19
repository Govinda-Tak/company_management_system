package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departments",indexes = {@Index(name="name_index",columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"employee","project"})
public class Department extends BaseModel {
	
	
	@jakarta.validation.constraints.NotBlank(message = "Department name required !!")
	@Column(length = 200,nullable = false,unique = true)
	@Length(min = 2,max = 200,message = "department length should be >=2 and <= 200 character !!")
	private String name;
	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Employee> employee; 
	
	@ManyToMany
	@JoinTable(name = "departments_projects",joinColumns = @JoinColumn(name="project_id",nullable = false),inverseJoinColumns = @JoinColumn(name="department_id",nullable = false))
	private List<Project> project;



	public Department(Long id, String name) {
		super(id);
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	
	public Department( String name) {
		super();
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	

	//Bi-directional binding for employee_department
	public void addEmployee(Employee newEmployee)
	{
		employee.add(newEmployee);
		newEmployee.setDepartment(this);
	}
	public void removeEmployee(Employee oldEmployee)
	{
		employee.remove(oldEmployee);
		oldEmployee.setDepartment(null);
	}
	
	//Bi-directional binding for project_department
		public void addProject(Project newProject)
		{
			project.add(newProject);
			newProject.getDepartment().add(this);
		}
		public void removeProject(Project oldProject)
		{
			project.remove(oldProject);
			oldProject.getDepartment().remove(this);
		}
}
