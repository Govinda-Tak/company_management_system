package com.example.demo.Dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AllProjectsWithAllDetailsResponseDto {
	
	private List<DetailedProjectResponseDto> projectList;
	private  AllProjectsWithAllDetailsResponseDto.PageInformation pageInfo; 
	

	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class PageInformation{
		
		private int pageNumber;
		private int pageSize;
		private int totalElements;
		private int totalPages;
	}

}
