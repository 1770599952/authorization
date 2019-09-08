package com.ycu.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ycu.piereport.Option;
import com.ycu.service.OrderPieReportService;

@Controller
@RequestMapping("/orderPieReport")
public class OrderPieReportController {
	
	@Autowired
	private OrderPieReportService orderPieReportService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "/report/orderPieCount";
	}
	
	@ResponseBody
	@RequestMapping(value="/count" , method = RequestMethod.GET)
	public Option count() {
		Option option = orderPieReportService.count();		
		return option;
	}
}
