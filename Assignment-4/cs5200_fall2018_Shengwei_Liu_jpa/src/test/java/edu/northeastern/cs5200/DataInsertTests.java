package edu.northeastern.cs5200;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.daos.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInsertTests {
	
	@Autowired
	UniversityDao ud;
	
	private static boolean initialized = false;
	
	@Before
	public void testDelete() {
		if (!initialized) {
			ud.truncateDatabase();
			initialized = true;
		}
	}
	@Test
	public void testACreateStudents() {
		Student student = new Student("alice","alice","Alice","Wonderland", 2020, 12000);
		ud.createStudent(student);
		student = new Student("bob","bob","Bob", "Hope", 2021, 23000);
		ud.createStudent(student);
		student = new Student("charlie","charlie","Charlie", "Brown",  2019, 21000);
		ud.createStudent(student);
		student = new Student("dan","dan","Dan", "Craig", 2019, 0);
		ud.createStudent(student);
		student = new Student("edward","edward","Edward", "Scissorhands", 2022, 11000);
		ud.createStudent(student);
		student = new Student("frank","frank","Frank", "Herbert", 2018, 0);
		ud.createStudent(student);
		student = new Student("gregory","gregory","Gregory", "Peck", 2023, 10000);
		ud.createStudent(student);			
	}
	
	@Test
	public void testBCreateFaculty() {
		Faculty faculty = new Faculty( "alan",  "alan", "Alan", "Turin", "123A", true);
		ud.createFaculty(faculty);
		faculty = new Faculty("ada" , "ada" , "Ada" , "Lovelace", "123B", true);
		ud.createFaculty(faculty);
	}
	
	@Test
	public void testCCreateCourses() {
		Course course = new Course("CS1234");
		course.setAuthor(ud.findFacultyByName("Alan"));
		ud.createCourse(course);	
		course = new Course("CS2345");
		course.setAuthor(ud.findFacultyByName("Alan"));
		ud.createCourse(course);		
		course = new Course("CS3456");
		course.setAuthor(ud.findFacultyByName("Ada"));
		ud.createCourse(course);
		course = new Course("CS4567");
		course.setAuthor(ud.findFacultyByName("Ada"));
		ud.createCourse(course);		
	}
	
	@Test
	public void testDCreateSections() {
		Section section = new Section("SEC4321",50);
		Course course = ud.findCourseByLabel("CS1234");
		System.out.println(course.getAuthor());
		section.setCourse(course);
		ud.createSection(section);
		section = new Section("SEC5432",50);
		section.setCourse(ud.findCourseByLabel("CS1234"));
		ud.createSection(section);
		section = new Section("SEC6543",50);
		section.setCourse(ud.findCourseByLabel("CS2345"));
		ud.createSection(section);
		section = new Section("SEC7654",50);
		section.setCourse(ud.findCourseByLabel("CS3456"));
		ud.createSection(section);		
	}
	
	
	@Test
	public void testECreateEnrollmeny() {
		Boolean e1 = ud.enrollStudentInSection(ud.findStudentByName("Alice"), ud.findSectionByTitle("SEC4321"));
		Boolean e2 = ud.enrollStudentInSection(ud.findStudentByName("Alice"), ud.findSectionByTitle("SEC5432"));
		Boolean e3 = ud.enrollStudentInSection(ud.findStudentByName("Bob"), ud.findSectionByTitle("SEC5432"));
		Boolean e4 = ud.enrollStudentInSection(ud.findStudentByName("Charlie"), ud.findSectionByTitle("SEC6543"));
		assert(e1);
		assert(e2);
		assert(e3);
		assert(e4);
	}
}
