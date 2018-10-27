package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Developer extends Person{
	
	private String developer_key; 
    
    Collection<Website> websites;
    
    public Developer(){
    	super();
    }
    //constructor 1.a
	public Developer(int id, String developer_key,String first_name, String last_name) {
		super(id,first_name, last_name, "neu", "124", "liu.shen@husky.neu.edu",new Date(2018-10-26));
		this.developer_key = developer_key;
	}
	//constructor 1.b
	public Developer(int id, String developer_key,String first_name, String last_name,String username,
			String password,String email, Date dob) {
		super(id,first_name,last_name,username,password,email,dob);
		this.developer_key = developer_key;
	}
	//constructor 1.c
	public Developer(int id, String developer_key,String first_name, String last_name,String username,
			String password,String email, Date dob,Collection<Phone> phones, Collection<Address> addresses) {
		super(id, first_name, last_name, username, password, email, dob,addresses, phones);
		this.developer_key = developer_key;
	}
	
	//setters and getters
	public String getDeveloper_key() {
		return developer_key;
	}
	public void setDeveloper_key(String developer_key) {
		this.developer_key = developer_key;
	}
	 public Collection<Website> getWebsites() {
		return websites;
	}
	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}

}

