package edu.northeastern.cs5200.daos;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
@Transactional
public class UniversityDao {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	public void truncateDatabase() {
		
		enrollmentRepository.deleteAll();
		sectionRepository.deleteAll();
		courseRepository.deleteAll();	
		personRepository.deleteAll();
	}
	public Faculty createFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	public Section createSection(Section section) {
		return sectionRepository.save(section);
	}
	public Course addSectionToCourse(Section section, Course course) {
		course.addSections(section);
		return courseRepository.save(course);
	}
	public Faculty setAuthorForCourse(Faculty faculty, Course course) {
		faculty.authorClasses(course);
		return facultyRepository.save(faculty);
	}
	public Boolean enrollStudentInSection(Student student, Section section) {
		Enrollment enrollment = enrollmentRepository.save(new Enrollment());
		student.addEnrollments(enrollment);
		studentRepository.save(student);
		section.addEnrollments(enrollment);
		sectionRepository.save(section);
		enrollmentRepository.save(enrollment);
		return section.getEnrollments().contains(enrollment)&& student.getEnrolled().contains(enrollment);	
	}
	public List<Person> findAllUsers(){
		return (List<Person>) personRepository.findAll();
	}
	public List<Faculty> findAllFaculty(){
		return (List<Faculty>) facultyRepository.findAll();
	}
	public List<Student> findAllStudents(){
		return (List<Student>) studentRepository.findAll();
	}
	public List<Course> findAllCourses(){
		return (List<Course>) courseRepository.findAll();
	}
	public List<Section> findAllSections(){
		return (List<Section>) sectionRepository.findAll();
	}
	public List<Course> findCoursesForAuthor(Faculty faculty){
		return faculty.getCourses();
	}
	public List<Section> findSectionForCourse(Course course){
		return course.getSections();
	}
	@SuppressWarnings("null")
	public List<Student> findStudentsInSection(Section section){
		List<Student> ret = null;
		List<Enrollment> enrolls = section.getEnrollments();
		if (enrolls!=null) {
			for(Enrollment e: enrolls) {
				ret.add(e.getEnrolledStudent());
			}	
		}
		return ret;
	}
	
	@SuppressWarnings("null")
	public List<Section> findSectionsForStudent(Student student){
		List<Section> ret=null;
		List<Enrollment> enrollments = student.getEnrolled();
		if (enrollments!=null) {
			for(Enrollment e: enrollments) {
				ret.add(e.getEnrolledSection());
			}	
		}
		return ret;
	}
	
	public Faculty findFacultyByName(String firstname) {
		return facultyRepository.findByName(firstname);
	}
	
	public Course findCourseByLabel(String label) {
		return courseRepository.findByLabel(label);
	}
	
	public Section findSectionByTitle(String title) {
		return sectionRepository.findByName(title);
	}
	
	public Student findStudentByName(String firstname) {
		return studentRepository.findByName(firstname);
	}
}
