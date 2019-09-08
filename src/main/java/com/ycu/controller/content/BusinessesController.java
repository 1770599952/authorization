package com.ycu.controller.content;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ycu.constant.DicTypeConst;
import com.ycu.constant.PageCodeEnum;
import com.ycu.dto.BusinessDto;
import com.ycu.service.BusinessService;
import com.ycu.service.DicService;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {

	@Resource
	private DicService dicService;

	@Resource
	private BusinessService businessService;

	/**
	 * 商户列表
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(Model model, BusinessDto dto) {
		model.addAttribute("list", businessService.searchByPage(dto));
		model.addAttribute("searchParam", dto);
		return "/content/businessList";
	}

	/**
	 * 删除商户
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String remove(@PathVariable("id") Long id,RedirectAttributes attr) {
		if(businessService.remove(id)) {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		} else {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
		// 这里不能转发哦，因为是DELETE请求
		return "redirect:/businesses";
	}

	/**
	 * 商户新增页初始化
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addInit(Model model) {
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		return "/content/businessAdd";
	}

	/**
	 * 商户新增
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String add(BusinessDto dto,RedirectAttributes attr) {
		// RedirectAttributes:是Spring mvc 3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转
		// 第一种： 
		//		attr.addAttribute("param", value);  
		//		这种方式就相当于重定向之后，在url后面拼接参数，这样在重定向之后的页面或者控制器再去获取url后面的参数就可以了，但这个方式因为是在url后面添加参数的方式，所以暴露了参数，有风险
		//	第二种： 
		//		attr.addFlashAttribute("param", value);
		//		这种方式也能达到重新向带参，而且能隐藏参数，其原理就是放到session中，session在跳到页面后马上移除对象。所以你刷新一下后这个值就会丢掉
		if(businessService.add(dto)) {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
			return "redirect:/businesses";
		} else {
			attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
			return "redirect:/businesses/addPage";
		}
	}

	/**
	 * 商户修改页初始化
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String modifyInit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj", businessService.getById(id));
		return "/content/businessModify";
	}

	/**
	 * 商户修改
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public String modify(Model model,BusinessDto dto) {
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj",dto);
		if (businessService.modify(dto)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
		}
		return "/content/businessModify";
	}
}