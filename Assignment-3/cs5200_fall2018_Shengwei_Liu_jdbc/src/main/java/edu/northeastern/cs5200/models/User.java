package edu.northeastern.cs5200.models;


import java.sql.Date;
import java.util.Collection;

public class User extends Person{

	private String user_key;
	private boolean userAgreement;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	//constructor 2.a
	public User(int id, String first_name, String last_name) {
		super(id, first_name, last_name, null, null, null, null);
		userAgreement=false;
	}
	//constructor for insert data, is similar to 1.b in Developer.java
	public User(int id, String user_key,String first_name, String last_name,String username,
			String password,String email, Date dob) {
		super(id,first_name,last_name,username,password,email,dob);
		this.user_key = user_key;
	}
	//constructor for insert data, is similar to 1.c in Developer.java
		public User(int id, String user_key,String first_name, String last_name,String username,
				String password,String email, Date dob,Collection<Phone> phones, Collection<Address> addresses) {
			super(id, first_name, last_name, username, password, email, dob,addresses, phones);
			this.user_key = user_key;
		}
	public User(String user_key, boolean userAgreement) {
		super();
		this.user_key = user_key;
		this.userAgreement = userAgreement;
	}
	//setters and getters
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public boolean getUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}

}
