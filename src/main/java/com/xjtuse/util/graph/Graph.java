package com.xjtuse.util.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xjtuse.model.MyDate;
import com.xjtuse.model.Staff;

public class Graph {
	private class Node{
		int type;
		int state;
		Staff staff;
		MyDate date;
		List nodeList;
		
		Node(int type, Object para){
			this.type = type;
			state = 0;
			if(type == 0)
				this.staff = (Staff) para;
			else
				this.date = (MyDate) para;
			nodeList = new ArrayList();
		}
	}
	private class Match{
		Staff staff;
		MyDate date;
		Match(Staff staff, MyDate date){
			this.staff = staff;
			this.date = date;
		}
	}
	
	List graph;
	List match;
	
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
	
	/**
	 * 匹配算法
	 * 找到增广路，删增边
	 * @param staff
	 * @param date
	 * @param sum
	 * @return 1 for 可以匹配
	 */
	int match(List staff, List date,int sum){
		init(staff,date);
		int check =0;
		match = new ArrayList();
		do{
			List road = new ArrayList();
			check = findRoad(road);
			if(check ==1){
				Iterator itr = road.iterator();
				Node ns = (Node) itr.next();
				ns.state = 1;
				int type = 1;
				while(itr.hasNext()){
					Node ne = (Node) itr.next();
					ne.state = 1;
					Match m = null;
					if(ns.type==0){
						m = new Match(ns.staff, ne.date);
					}
					else{
						m = new Match(ne.staff,ns.date);
					}
					if(type==1){
						match.add(m);
						type=0;
					}
					else{
						match.remove(m);
						type=1;
					}
				}
			}
		}while(check==1);
		if( sum>match.size() )
			return 0;
		return 1;
	}
	
	/**
	 * 延伸增广路
	 * @param r
	 * @return
	 */
	int strech(ArrayList r,int st){
		Node nf = (Node) r.get(r.size()-1);
		Iterator itg = nf.nodeList.iterator();
		while(itg.hasNext()){
			//寻找下一个节点
			Node n = (Node) itg.next();
			//条件2：不能已出现在路中
			int check = 1;
			for(int i=0;i<r.size();i++){
				Node nt = (Node) r.get(i);
				if(nt.type ==0 && n.type==0 && nt.staff==n.staff){
					check=0;
					break;
				}
				if(nt.type ==1 && n.type==1 && nt.date==n.date){
					check=0;
					break;
				}
			}
			if(check ==0)
				continue;
			//条件3：路交错
			Staff s = null;
			MyDate d = null;
			if(nf.type==0){
				s=nf.staff;
				d=n.date;
			}else{
				s=n.staff;
				d=nf.date;
			}
			check=0;
			Iterator itm = match.iterator();
			while(itm.hasNext()){
				Match m = (Match) itm.next();
				if(m.staff==s && m.date==d){
					check=1;
					break;
				}
			}
			if(check==st){
				//st 0 未匹配边 1 匹配边
				r.add(n);
				if(n.state==0){
					return 1;
				}
				int stn;
				if(st==0)
					stn=1;
				else
					stn=0;
				if( strech(r,stn)==1 )
					return 1;				
				r.remove(r.size()-1);
			}
		}
		return 0;
	}
	
	/**
	 * 找到增广路
	 * @param road
	 * @return
	 */
	int findRoad(List road){
		Iterator itg = graph.iterator();
		while(itg.hasNext()){
			Node n = (Node) itg.next();
			if(n.state==0){
				ArrayList r = new ArrayList();
				r.add(n);
				if(strech(r,0)==1){
					road=r;
					return 1;
				}
			}
		}
		return 0;
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
