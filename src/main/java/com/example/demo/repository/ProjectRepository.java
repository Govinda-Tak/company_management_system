package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<List<Project>> findAllByStartDate(LocalDate startingDate);

	
	
	
}
