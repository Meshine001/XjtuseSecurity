package com.xjtuse.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	//人员权值，只有在值班之后会变动，排班并不改变该值。   越高越容易轮空 
	int priority;
	//1 sun ... 7 sat
	int inconvenient;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Staff(String name, int priority, int inconvenient) {
		super();
		this.name = name;
		this.priority = priority;
		this.inconvenient = inconvenient;
	}


	
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getInconvenient() {
		return inconvenient;
	}
	public void setInconvenient(int inconvenient) {
		this.inconvenient = inconvenient;
	}


	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", priority=" + priority + ", inconvenient=" + inconvenient + "]";
	}




	
	
}
