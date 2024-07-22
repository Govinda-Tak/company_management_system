package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"employees","departments"})
public class Project extends BaseModel {
	@jakarta.validation.constraints.NotBlank(message = "project name required !!")
	@Length(min = 2,max = 200,message = "project name length in between 2 and 200 character !!")
	private String name;
	
	@PastOrPresent(message = "project starting date must be in present or past !!")
	private LocalDate startDate;
	
	@FutureOrPresent(message = "prroject ending date must be in future or present !!")
	private LocalDate endDate;
	
	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;
	
	@ManyToMany(mappedBy = "projects")
	private List<Department> departments;
	
	
	public Project(Long id, String name,LocalDate startDate,LocalDate endDate) {
		super(id);
		// TODO Auto-generated constructor stub
		this.endDate=endDate;
		this.startDate=startDate;
		this.name=name;
	}
	
	public Project( String name,LocalDate startDate,LocalDate endDate) {
		super();
		// TODO Auto-generated constructor stub
		this.endDate=endDate;
		this.startDate=startDate;
		this.name=name;
	}
	
	public void addEmployee(Employee emp)
	{
		this.getEmployees().add(emp);
		emp.getProjects().add(this);
	}
	public void removeEmployee(Employee emp)
	{
		this.getEmployees().remove(emp);
		emp.getProjects().remove(this);
	}
	
	
}
