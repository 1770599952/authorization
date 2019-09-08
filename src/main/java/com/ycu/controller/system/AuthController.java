package com.ycu.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycu.constant.DicTypeConst;
import com.ycu.service.DicService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private DicService dicService;

	@RequestMapping
	public String index(Model model) {
		model.addAttribute("httpMethodList", dicService.getListByType(DicTypeConst.HTTP_METHOD));
		return "/system/auth";
	}
}