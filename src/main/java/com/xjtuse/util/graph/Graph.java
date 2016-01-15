package com.xjtuse.security.util.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xjtuse.security.model.MyDate;
import com.xjtuse.security.model.Staff;

public class Graph {
	private class Node{
		int type;
		Staff staff;
		MyDate date;
		List nodeList;
		
		Node(int type, Object para){
			this.type = type;
			if(type == 0)
				this.staff = (Staff) para;
			else
				this.date = (MyDate) para;
			nodeList = new ArrayList();
		}
	}
	
	List graph;
	
	public Graph() {
		// TODO Auto-generated constructor stub
		graph = new ArrayList();
	}
	
	/**
	 * zyp
	 * 产生随机数
	 * @param sum
	 * @param i
	 * sum中产生i个数
	 * @return
	 */
	private List createRandom(int sum, int i) {
		// TODO Auto-generated method stub
		List rlist = new ArrayList();
		while(i>0){
			i--;
			boolean check= false;
			while(!check){
				int t = (int) (Math.random()*(sum+1));
				if(t>0 && t<=sum){
					check=true;
					Iterator itr = rlist.iterator();
					while(itr.hasNext()){
						int data = (Integer) itr.next();
						if(data==t){
							check = false;
						}
					}
					if(check){
						rlist.add(t);
					}
				}
			}
		}
		return rlist;
	}
	
	
	/**
	 * zyp
	 * 筛选
	 * @param staff
	 * @param num
	 * 从 staff
	 * 筛选出n个人
	 * @return
	 */
	private void filter(List staff, int num){
		while(staff.size()>num){
			int min=1000000;
			int sum=0;
			Iterator its = staff.iterator();
			while(its.hasNext()){
				Staff st = (Staff) its.next();
				if(st.getPriority()<min){
					min=st.getPriority();
					sum=1;
				}
				else if(st.getPriority()==min)
					sum++;
			}
			if((staff.size()-sum)>=num){
				its = staff.iterator();
				while(its.hasNext()){
					Staff st = (Staff) its.next();
					if(st.getPriority()==min){
						staff.remove(st);
						its = staff.iterator();
					}
				}
			}
			else{
				List lucky = createRandom(sum,staff.size()-num);
				int i=0;
				its = staff.iterator();
				List dellist = new ArrayList();
				while(its.hasNext()){
					Staff st = (Staff) its.next();
					if(st.getPriority()==min){
						i++;
						if(lucky.contains(i)){
							dellist.add(st);
						}
					}
				}
				Iterator itdel = dellist.iterator();
				while(itdel.hasNext()){
					staff.remove(itdel.next());
				}
			}
		}
	}

	/**
	 * zyp
	 * 初始化图
	 * @param staff
	 * @param date
	 * @return 如果人数比日期少，返回0，否则1
	 */
	private int init(List staff, List date){
		
		
		if(date.size()>staff.size())
			return 0;
		
		Iterator itd = date.iterator();
		while(itd.hasNext()){
			MyDate dt = (MyDate) itd.next();
			Node dn = new Node(1,dt);
			graph.add(dn);
		}
		
		Iterator its = staff.iterator();
		while(its.hasNext()){
			Staff st = (Staff) its.next();
			Node sn = new Node(0,st);
			graph.add(sn);
			//添加关系
			Iterator itg = graph.iterator();
			while(itg.hasNext()){
				Node n = (Node) itg.next();
				if(n.type==1){
					if(n.date.getDay_week()!=7 && n.date.getDay_week()!=1 && n.date.getDay_week()!=st.getInconvenient()){
						n.nodeList.add(sn);
						sn.nodeList.add(n);
					}
				}
			}
		}
		int luckyday=0;
		Iterator itg = graph.iterator();
		while(itg.hasNext()){
			Node n = (Node) itg.next();
			if(n.type==1){
				if(n.date.getDay_week()==1 || n.date.getDay_week()==7){
					luckyday++;
				}
			}
		}
		filter(staff, luckyday);
		itg = graph.iterator();
		while(itg.hasNext()){
			Node n = (Node) itg.next();
			if(n.type==1){
				if(n.date.getDay_week()==1 || n.date.getDay_week()==7){
					Iterator itgs = graph.iterator();
					while(itgs.hasNext()){
						Node ns = (Node) itgs.next();
						if(ns.type==0){
							if(staff.contains(ns.staff)){
								n.nodeList.add(ns);
								ns.nodeList.add(n);
							}
						}
					}
				}
			}
		}
		
		return 1;
	}
	
	int match(){
		while()
			
		return 1;
	}
	
	List findRoad(){
		return null;
	}
	
	public int test(List staff, List date){
		int i = init(staff,date);
		Iterator itg = graph.iterator();
		while(itg.hasNext()){
			Node n = (Node) itg.next();
			if(n.type==0){
				System.out.println(n.staff.getName()+": ");
				Iterator itl = n.nodeList.iterator();
				while(itl.hasNext()){
					Node nt = (Node) itl.next();
					if(nt.type==0){
						System.out.print(nt.staff.getName()+"; ");
					}
					else{
						System.out.println(nt.date.toString()+"; ");
					}
				}
			}
			else{
				System.out.println(n.date.toString()+": ");
				Iterator itl = n.nodeList.iterator();
				while(itl.hasNext()){
					Node nt = (Node) itl.next();
					if(nt.type==0){
						System.out.print(nt.staff.getName()+"; ");
					}
					else{
						System.out.println(nt.date.toString()+"; ");
					}
				}
			}
		}
		return i;
	}
}
