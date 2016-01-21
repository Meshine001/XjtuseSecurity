package com.xjtuse.model;

import java.util.Calendar;
import java.util.Date;

public class MyDate {
	
	Date date;
	//1= 周日   7=周6  以此类推
	int day_week;
	
	public MyDate(Date date){
		this.date = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day_week = cal.get(Calendar.DAY_OF_WEEK);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day_week = cal.get(Calendar.DAY_OF_WEEK);
	}

	public int getDay_week() {
		return day_week;
	}

	public void setDay_week(int day_week) {
		this.day_week = day_week;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return date.toString();
	}
}
