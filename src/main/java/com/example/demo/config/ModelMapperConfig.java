package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

import com.example.demo.Dto.request.DepartmentRequestDto;
import com.example.demo.Dto.response.FullDepartmentResponse;
import com.example.demo.Dto.response.FullProjectResponseDto;
import com.example.demo.model.Department;
import com.example.demo.model.Project;


@org.springframework.context.annotation.Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Set field access level to private
        modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);

        // Optionally, you can set other configurations like matching strategies
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        
        modelMapper.addConverter(new CollectionConverter<>());

        return modelMapper;
    }
    @Bean
    public TypeMap<DepartmentRequestDto, Department> departmentRequestDtoToDepartmentMap(ModelMapper modelMapper) {
        return modelMapper.createTypeMap(DepartmentRequestDto.class, Department.class)
                         .addMappings(mapper -> {
                             mapper.skip(Department::setId); // Ensure ID is not overwritten
                         });
    }
    @Bean
    public TypeMap<Department,FullDepartmentResponse> departmentToFullDepartmentResponse(ModelMapper modelMapper) {
        return modelMapper.createTypeMap(Department.class, FullDepartmentResponse.class)
                         .addMappings(mapper -> {
                             mapper.skip(Department::getEmployee,FullDepartmentResponse::setEmployee);
                             mapper.skip(Department::getProject, FullDepartmentResponse::setProject);
                             // Ensure ID is not overwritten
                         });
    }
    @Bean
    public TypeMap<Project, FullProjectResponseDto> ProjectToFullProjectResponseDto(ModelMapper modelMapper)
    {
    	return modelMapper.createTypeMap(Project.class, FullProjectResponseDto.class)
    			.addMappings(mapper->{mapper.skip(Project::getEmployee, FullProjectResponseDto::setEmployee);
    			mapper.skip(Project::getDepartment,FullProjectResponseDto::setDepartment);
    			});
    }
}
