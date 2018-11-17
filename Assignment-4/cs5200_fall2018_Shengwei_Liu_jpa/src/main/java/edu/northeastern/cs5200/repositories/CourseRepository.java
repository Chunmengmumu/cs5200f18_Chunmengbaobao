package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.*;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query("select c from Course c where c.label=:label")
	public Course findByLabel(@Param("label") String label);
}
