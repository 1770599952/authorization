package com.ycu.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {

	/**
	 * 登录成功后，后台管理首页
	 */
	@RequestMapping
	public String init() {
		return "/system/index";
	}
	
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String main() {
		return "/system/main";
	}
}
