package com.ycu.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycu.report.Option;
import com.ycu.service.OrderReportService;

@Controller
@RequestMapping("/monthReport")
public class MonthReportController {
	
	@Autowired
	private OrderReportService orderReportService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "/report/monthCount";
	}
	
	@ResponseBody
	@RequestMapping(value="/count" , method = RequestMethod.GET)
	public Option count() {
		Option option = orderReportService.monthCount();
		return option;
	}
}