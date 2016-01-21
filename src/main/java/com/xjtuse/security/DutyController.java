package com.xjtuse.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RequestMapping("duty")
@Controller
public class DutyController {
	
	@RequestMapping("")
	public String index(){
		
		return "duty/index";
	}
	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validateSecurityCode(String securityCode){
		System.out.println(securityCode);
		return "duty/manage";
	}
	
	@RequestMapping(value = "/init",method=RequestMethod.POST)
	public String initData(@RequestParam(value="upload")
	CommonsMultipartFile upload,HttpServletRequest request){		
		if(!upload.isEmpty()){
			System.out.println(upload.getOriginalFilename());
		}
		
		System.out.println("!!");
		return "duty/manage";
	}
}
