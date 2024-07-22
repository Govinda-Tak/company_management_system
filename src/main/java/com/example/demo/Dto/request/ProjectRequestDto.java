package com.example.demo.Dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectRequestDto {
	@jakarta.validation.constraints.NotBlank(message = "project name required !!")
	@Length(min = 2,max = 200,message = "project name length in between 2 and 200 character !!")
	private String name;
	
	@PastOrPresent(message = "project starting date must be in present or past !!")
	private LocalDate startDate;
	
	@FutureOrPresent(message = "prroject ending date must be in future or present !!")
	private LocalDate endDate;
	
	
	
	
	
	
	
}
