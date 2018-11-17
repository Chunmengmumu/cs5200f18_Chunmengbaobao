package edu.northeastern.cs5200.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	public Section() {}
	public Section( String title,int seats) {
		super();
		this.seats = seats;
		this.title = title;
	}
	private int seats;
	private String title;
	@ManyToOne()
	private Course courseOfSections;
	@OneToMany(mappedBy="enrolledSection")
	private List<Enrollment> enrollments;
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Course getCourse() {
		return courseOfSections;
	}
	public void setCourse(Course course) {
		this.courseOfSections = course;
		if(!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
	}
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	public void addEnrollments(Enrollment e) {
		this.enrollments.add(e);
		if(e.getEnrolledSection()!=this) {
			e.setEnrolledSection(this);
			seats -= 1;
		}
	}

}
