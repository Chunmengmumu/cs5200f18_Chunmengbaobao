package edu.northeastern.cs5200.models;

import java.util.*;
import javax.persistence.*;

@Entity(name="Faculty")
@DiscriminatorValue(value = "Faculty")
public class Faculty extends Person{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String office;
	private Boolean tenured;
	@OneToMany(mappedBy="author")
	private List<Course> courses=null;
	public Faculty(){super();}
	public Faculty(String username, String password, String firstname, String lastname, String office,
			Boolean tenured) {
		super(username, password, lastname, firstname);
		this.office = office;
		this.tenured = tenured;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void authorClasses(Course course) {
		this.courses.add(course);
		if(course.getAuthor()!=this) {
			course.setAuthor(this);
		}	
	}
}
