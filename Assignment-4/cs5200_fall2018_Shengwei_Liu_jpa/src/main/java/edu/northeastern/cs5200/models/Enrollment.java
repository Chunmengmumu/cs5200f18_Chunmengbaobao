package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int grade;
	private String feedback;
	public Enrollment() {}
	@ManyToOne
	private Section enrolledSection;
	@ManyToOne
	private Student enrolledStudent;
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Section getEnrolledSection() {
		return enrolledSection;
	}
	public void setEnrolledSection(Section enrolledSection) {
		this.enrolledSection = enrolledSection;
		if(!enrolledSection.getEnrollments().contains(this)) {
			enrolledSection.getEnrollments().add(this);
		}
	}
	public Student getEnrolledStudent() {
		return enrolledStudent;
	}
	public void setEnrolledStudent(Student enrolledStudent) {
		this.enrolledStudent = enrolledStudent;
		if(!enrolledStudent.getEnrolled().contains(this)) {
			enrolledStudent.getEnrolled().add(this);
		}
	}
	
}
