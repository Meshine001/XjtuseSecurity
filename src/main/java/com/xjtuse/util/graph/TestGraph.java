package com.xjtuse.util.graph;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.xjtuse.model.MyDate;
import com.xjtuse.model.Staff;



public class TestGraph {
	public static void main(String[] args) {
		Graph g = new Graph();
//		List a = g.test(10, 9);
//		System.out.println(a.toString());
		Staff st1 = new Staff();
		st1.setName("1");
		st1.setPriority(1);
		Staff st2 = new Staff();
		st2.setName("2");
		st2.setPriority(2);
		Staff st3 = new Staff();
		st3.setName("3");
		st3.setPriority(2);
		Staff st4 = new Staff();
		st4.setName("4");
		st4.setPriority(1);
		Staff st5 = new Staff();
		st5.setName("5");
		st5.setPriority(1);
		Staff st6 = new Staff();
		st6.setName("6");
		st6.setPriority(1);
		Staff st7 = new Staff();
		st7.setName("7");
		st7.setPriority(1);
		List l = new ArrayList();
		l.add(st1);
		l.add(st2);
		l.add(st3);
		l.add(st4);
		l.add(st5);
		l.add(st6);
		l.add(st7);
		
		
		Date d1 = new Date(System.currentTimeMillis());
		Date d2 = new Date(System.currentTimeMillis());
		d2.setDate(d2.getDate()+1);
		Date d3 = new Date(System.currentTimeMillis());
		d3.setDate(d3.getDate()+2);
		Date d4 = new Date(System.currentTimeMillis());
		d4.setDate(d4.getDate()+3);
		Date d5 = new Date(System.currentTimeMillis());
		d5.setDate(d5.getDate()+4);
		Date d6 = new Date(System.currentTimeMillis());
		d6.setDate(d6.getDate()+5);
		Date d7 = new Date(System.currentTimeMillis());
		d7.setDate(d7.getDate()+6);
		MyDate m1 = new MyDate(d1);
		MyDate m2 = new MyDate(d2);
		MyDate m3 = new MyDate(d3);
		MyDate m4 = new MyDate(d4);
		MyDate m5 = new MyDate(d5);
		MyDate m6 = new MyDate(d6);
		MyDate m7 = new MyDate(d7);
		List d = new ArrayList();
		d.add(m1);
		d.add(m2);
		d.add(m3);
		d.add(m4);
		d.add(m5);
		d.add(m6);
		d.add(m7);
		g.test(l, d);
		
		
		System.out.println("**************");
	}

}
