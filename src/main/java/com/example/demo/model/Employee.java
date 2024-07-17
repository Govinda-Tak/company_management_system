package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.enums.Designation;
import com.example.demo.util.annotation.ValidDateOfBirth;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"project"})
public class Employee extends BaseModel {
	@NotBlank(message = "Employee name required !!")
	@Column(nullable = false)
	@Length(max = 255,min = 2,message = "max. name <=255 and >=2 character required !!")
	private String name;
	
	@Column(name = "contact_no")
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String contactNo;
	@ValidDateOfBirth(message = "age must be in between 18 to 60 years old !!")
	@Past(message = "Date of birth must be in the past")
	@Column(name = "dob",nullable = false)
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "designation required !!")
	private Designation designation;
	@NotBlank(message = "email required !!")
	@Email
	private String email;
	
	@NotBlank(message = "password required !!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "department_id",nullable = false)
	private Department department;
	
	@ManyToMany
	@JoinTable(name = "projects_employees",joinColumns = @JoinColumn(name="project_id",nullable = false),inverseJoinColumns = @JoinColumn(name="employee_id",nullable = false))
	private List<Project> project;
	
	@Embedded
	private Address address;

	public Employee(
			@NotBlank(message = "Employee name required !!") @Length(max = 255, min = 2, message = "max. name <=255 and >=2 character required !!") String name,
			@Pattern(regexp = "^[6-9]\\d{9}$") String contactNo,
			@Past(message = "Date of birth must be in the past") LocalDate dateOfBirth,
			@NotNull(message = "designation required !!") Designation designation,
			@NotBlank(message = "email required !!") @Email String email,
			@NotBlank(message = "password required !!") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$") String password,
			Address address) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.dateOfBirth = dateOfBirth;
		this.designation = designation;
		this.email = email;
		this.password = password;
		this.address = address;
		
	}
	


	
	

	
}
