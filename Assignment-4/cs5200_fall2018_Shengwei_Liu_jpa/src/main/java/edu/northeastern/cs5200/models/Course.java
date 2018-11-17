package edu.northeastern.cs5200.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	public Course() {}
	public Course(String label) {
		super();
		this.label = label;
	}
	@ManyToOne()
	private Faculty author;
	@OneToMany(mappedBy="courseOfSections")
	private List<Section> sections=null;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public Faculty getAuthor() {
		return author;
	}
	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getCourses().contains(this)) {
			author.getCourses().add(this);
		}
	}
	public void addSections(Section section) {
		this.sections.add(section);
		if (section.getCourse()!=this) {
			section.setCourse(this);
		}
	}
}