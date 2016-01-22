package com.xjtuse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String userpsd;
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	public User(String username, String userpsd) {
		super();
		this.username = username;
		this.userpsd = userpsd;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpsd() {
		return userpsd;
	}

	public void setUserpsd(String userpsd) {
		this.userpsd = userpsd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", userpsd=" + userpsd + "]";
	}

}
