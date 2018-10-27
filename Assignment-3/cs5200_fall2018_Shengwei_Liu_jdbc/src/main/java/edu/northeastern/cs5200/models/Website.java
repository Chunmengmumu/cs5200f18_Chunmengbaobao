package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Website{

	private int id;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;

	Collection<Page> pages; //foreign key
	Collection<Role> roles;
	Collection<Priviledge> priviledge;
	private Developer developer;
	
	public Website() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Website(int id, String name, String description, Date created, Date updated, int visits,
			Collection<Page> pages) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.pages = pages;
	}
	//constructor: 3.a 
	public Website(int id, String name, String description, Date created, Date updated, int visits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}
	
    //setters and getters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public Collection<Page> getPages() {
		return pages;
	}
	public void setPages(Collection<Page> pages) {
		this.pages = pages;
	}

}

