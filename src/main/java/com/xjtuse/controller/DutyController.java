package com.xjtuse.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xjtuse.entity.User;
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

	@RequestMapping("/")
	public String index() {

		return "duty/index";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateSecurityCode(String securityCode) {
		System.out.println(securityCode);
		User admin = userService.getUser(1);
		if (securityCode.equals(admin.getUserpsd())) {
			System.out.println("success");
			return "duty/manage";
		}

		return "duty/index";
	}

	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public String initData(@RequestParam(value = "upload") CommonsMultipartFile upload, HttpServletRequest request) {

		if (!upload.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("upload");
			String fileName = upload.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				upload.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				List<String> list = new ExcelHelper().readNamesFormExcel(targetFile.getAbsolutePath());
				if(list != null){
					for(String name : list){
						System.out.println("name::"+name);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "duty/manage";
	}
}
