package com.ycu.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ycu.constant.PageCodeEnum;
import com.ycu.dto.CommentDto;
import com.ycu.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	private CommentService commentService;

	// 评论页初始化
	@RequestMapping(method=RequestMethod.GET)
	public String init(Model model) {

		CommentDto commentDto = new CommentDto();
		model.addAttribute("list",commentService.searchByPage(commentDto));
		model.addAttribute("searchParam",commentDto);

		return "/content/commentList";
	}

	// 分页查询评论
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public String search(CommentDto commentDto,Model model) {

		model.addAttribute("list",commentService.searchByPage(commentDto));
		model.addAttribute("searchParam",commentDto);

		return "/content/commentList";
	}

	// 删除评论
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") Long id, Model model) {
		if(commentService.remove(id)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
		return "forward:/comments";
	}

}
