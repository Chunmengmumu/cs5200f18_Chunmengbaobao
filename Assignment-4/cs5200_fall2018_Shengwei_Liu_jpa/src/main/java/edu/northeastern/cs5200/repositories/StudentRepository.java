package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.*;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
	@Query("select p from Person p where p.firstname=:firstname")
	public Student findByName(@Param("firstname")String firstname);
}
