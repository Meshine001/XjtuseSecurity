package com.xjtuse.dao;

import java.util.List;

import com.xjtuse.entity.Staff;

public interface StaffDAO {
	public Staff addStaff(Staff staff);

	public List<Staff> getAllStaffs();

	public Staff getStaff(int id);

	public void deleteStaff(Staff staff);
}
