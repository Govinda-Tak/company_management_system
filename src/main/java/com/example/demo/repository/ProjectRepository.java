package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.example.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<List<Project>> findAllByStartDate(LocalDate startingDate);

	
	
	
}
