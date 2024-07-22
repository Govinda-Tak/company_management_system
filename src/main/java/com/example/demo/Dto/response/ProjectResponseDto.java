package com.example.demo.Dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectResponseDto  {
	private Long id;
	private String name;
	
	
	private LocalDate startDate;
	
	
	private LocalDate endDate;
	
	
	
	
}
