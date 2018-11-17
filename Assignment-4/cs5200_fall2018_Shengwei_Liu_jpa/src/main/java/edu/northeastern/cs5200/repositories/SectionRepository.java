package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Section;


public interface  SectionRepository extends CrudRepository<Section, Integer> {
	@Query("select s from Section s where s.title=:title")
	public Section findByName(@Param("title")String title);
}
