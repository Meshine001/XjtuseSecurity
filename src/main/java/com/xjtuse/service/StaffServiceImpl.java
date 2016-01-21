package com.xjtuse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtuse.dao.StaffDAO;
import com.xjtuse.entity.Staff;

@Service
public class StaffServiceImpl implements StaffService{

	private StaffDAO staffDao;
	@Autowired
	public void setStaffDao(StaffDAO staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public Staff addStaff(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.addStaff(staff);
	}

	@Override
	public List<Staff> getAllStaffs() {
		// TODO Auto-generated method stub
		return staffDao.getAllStaffs();
	}

	@Override
	public Staff getStaff(int id) {
		// TODO Auto-generated method stub
		return staffDao.getStaff(id);
	}

	@Override
	public void deleteStaff(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.deleteStaff(staff);
	}

}
