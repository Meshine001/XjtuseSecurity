package com.xjtuse.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.xjtuse.entity.Staff;
import com.xjtuse.model.MyDate;
import com.xjtuse.service.StaffService;
import com.xjtuse.service.StaffServiceImpl;
import com.xjtuse.service.UserService;
import com.xjtuse.util.Assign;

public class TstAssign {
	
	public static void main(String[] args) {
		StaffService sSer = new StaffServiceImpl();
		
		List dlist = new ArrayList();
		Date date=new Date(System.currentTimeMillis());
		for(int i=0;i<28;i++){
			MyDate myd = new MyDate(date,i%2);
			dlist.add(myd);
			if(i%2==1){
				Calendar calendar = new GregorianCalendar();
		        calendar.setTime(date);
		        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
		        date=calendar.getTime(); 
			}
		}
		for(int i=0;i<dlist.size();i++){
			System.out.println(dlist.get(i).toString());
		}
		Assign agn = new Assign(sSer.getAllStaffs(),dlist);
	}
}
