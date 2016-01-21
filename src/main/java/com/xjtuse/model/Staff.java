package com.xjtuse.model;

import java.util.Set;

public class Staff{
	int id;
	String name;
	//人员权值，只有在值班之后会变动，排班并不改变该值。   越高越容易轮空 
	int priority;
	//1 sun   7 sat
	int inconvenient;
	Set records;
	
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
	public Set getRecords() {
		return records;
	}
	public void setRecords(Set records) {
		this.records = records;
	}

}