package com.ycu.controller.content;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ycu.constant.PageCodeEnum;
import com.ycu.dto.OrdersDto;
import com.ycu.service.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService orderService;
	
	// 订单管理页面初始化
	@RequestMapping(method=RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {
		OrdersDto dto = new OrdersDto();
		model.addAttribute("list",orderService.searchByPage(dto));
		model.addAttribute("searchParam",dto);
		return "/content/orderList";

	}
	
	// 订单删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String remove(@PathVariable("id") Long id,RedirectAttributes attr) {
		if(orderService.remove(id)) {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		} else {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
		// 这里不能转发哦，因为是DELETE请求
		return "redirect:/orders";
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping("/search")
	public String search(Model model,OrdersDto dto) {
		model.addAttribute("list", orderService.searchByPage(dto));
		model.addAttribute("searchParam", dto);
		return "/content/orderList";
	}
}
