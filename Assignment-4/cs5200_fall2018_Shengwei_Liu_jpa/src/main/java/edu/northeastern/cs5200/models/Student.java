package edu.northeastern.cs5200.models;

import java.util.*;
import javax.persistence.*;

@Entity(name="Student")
@DiscriminatorValue(value = "Student")
public class Student extends Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int gradYear;
	private long scholarship;
	@OneToMany(mappedBy="enrolledStudent")
	private List<Enrollment> enrolled=null;
	public Student(){super();}
	public Student(String username, String password, String firstname,String lastname, int gradYear,
			long scholarship) {
		super(username, password, lastname, firstname);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	public int getGradYear() {
		return gradYear;
	}
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	public long getScholarship() {
		return scholarship;
	}
	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}
	public List<Enrollment> getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(List<Enrollment> enrolled) {
		this.enrolled = enrolled;
	}
	public void addEnrollments(Enrollment e) {
		this.enrolled.add(e);
		if(e.getEnrolledStudent()!=this) {
			e.setEnrolledStudent(this);
		}
	}
}