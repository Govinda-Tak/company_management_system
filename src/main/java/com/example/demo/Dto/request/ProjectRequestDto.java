package com.example.demo.Dto.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

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
