package com.xjtuse.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xjtuse.entity.Staff;
import com.xjtuse.entity.Student;
import com.xjtuse.entity.User;
import com.xjtuse.service.StaffService;
import com.xjtuse.service.StudentService;
import com.xjtuse.service.UserService;
import com.xjtuse.util.ExcelHelper;

@RequestMapping("duty")
@Controller
public class DutyController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private StudentService studentService;
	
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	private StaffService staffService;
	@Autowired
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@RequestMapping("/addStaffs")
	@ResponseBody
	public List<Staff> addStaffTestData(){
		for(int i=0;i<50;i++){
			staffService.addStaff(new Staff("User"+i, 1, 0));
		}
		
		return staffService.getAllStaffs();
	}
	@RequestMapping("/staffs")
	@ResponseBody
	public List<Staff> getStaffTestData(){

		
		return staffService.getAllStaffs();
	}
	
	

	@RequestMapping("/")
	public String index() {

		return "duty/index";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateSecurityCode(String securityCode,Model model) {
		System.out.println(securityCode);
		User admin = userService.getUser(1);
		if (securityCode.equals(admin.getUserpsd())) {
			List<Student> students = studentService.getAllStudents();
			//Need initialization
			if(students.isEmpty()){
				return "redirect:/duty/init";
			}
			model.addAttribute("students", students);
			return "redirect:/duty/manage";
		}
		
		model.addAttribute("message", "Security Code is invalid!!!!");
		return "home";
	}
	
	/**
	 * 跳转init
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(){
		return "duty/init";
	}
	/**
	 * 跳转manage
	 * @return
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(Model model){
		List<Student> students = studentService.getAllStudents();
		if(!students.isEmpty()){
			for(Student s:students){
				System.out.println(s);
			}
		}
		model.addAttribute("students", students);
		return "duty/manage";
	}

	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public String initData(@RequestParam(value = "upload") CommonsMultipartFile upload, HttpServletRequest request,Model model) {

		if (!upload.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("upload");
			String fileName = upload.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				upload.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//解析excel
			try {
				List<String> list = new ExcelHelper().readNamesFormExcel(targetFile.getAbsolutePath());
				if(list != null){
					for(String name : list){
						//System.out.println("name::"+name);
						studentService.addStudent(new Student(name));
					}
				}
				
				return "redirect:/duty/manage";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				model.addAttribute("message", "Failed!! Please check the excel.");
				return "duty/init";			}

		}

		model.addAttribute("message", "The file is empty!");
		return "duty/init";
	}
}
