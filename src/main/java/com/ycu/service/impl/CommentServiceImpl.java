package com.ycu.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ycu.bean.Business;
import com.ycu.bean.Comment;
import com.ycu.bean.Orders;
import com.ycu.bean.Page;
import com.ycu.constant.CommentStateConst;
import com.ycu.dao.CommentDao;
import com.ycu.dao.OrdersDao;
import com.ycu.dto.CommentDto;
import com.ycu.dto.CommentForSubmitDto;
import com.ycu.dto.CommentListDto;
import com.ycu.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentDao commentDao;
	@Resource
	private OrdersDao ordersDao;

	@Override
	public List<CommentDto> searchByPage(CommentDto commentDto) {

		List<CommentDto> result = new ArrayList<CommentDto>();
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDto,comment);
		Orders orders = new Orders();
		comment.setOrders(orders);
		Business business = new Business();
		orders.setBusiness(business);
		List<Comment> commentList = commentDao.selectByPage(comment);
		for (Comment ct: commentList) {
			CommentDto commentDtoTemp = new CommentDto();
			result.add(commentDtoTemp);
			BeanUtils.copyProperties(ct,commentDtoTemp);
		}
		return result;
	}

	@Override
	public boolean remove(Long id) {
		int updateRows = commentDao.delete(id);
		return updateRows == 1;
	}

	@Override
	@Transactional
	public boolean add(CommentForSubmitDto commentForSubmitDto) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForSubmitDto, comment);
		comment.setId(null);
		comment.setOrdersId(commentForSubmitDto.getId());
		comment.setCreateTime(new Date());
		// 保存评论
		commentDao.insert(comment);
		Orders orders = new Orders();
		orders.setId(commentForSubmitDto.getId());
		orders.setCommentState(CommentStateConst.HAS_COMMENT);
		// 更新订单评论状态
		ordersDao.update(orders);
		return true;
	}

	@Override
	public CommentListDto getListByBusinessId(Long businessId, Page page) {
		CommentListDto result = new CommentListDto();
		
		// 组织查询条件
		Comment comment = new Comment();
		Orders orders = new Orders();
		Business business = new Business();
		// 评论里包含了订单对象
		comment.setOrders(orders);
		// 订单对象里包含了商户对象
		orders.setBusiness(business);
		// 设置商户主键
		business.setId(businessId);
		// 前端app页码从0开始计算，这里需要+1
		page.setCurrentPage(page.getCurrentPage() + 1);
		// 设置分页条件
		comment.setPage(page);
		List<Comment> commentList = commentDao.selectByPage(comment);
		
		// 组织返回值
		List<CommentDto> data = new ArrayList<>();
		result.setData(data);
		for(Comment commentTemp : commentList) {
			CommentDto commentDto = new CommentDto();
			data.add(commentDto);
			BeanUtils.copyProperties(commentTemp, commentDto);
			// 隐藏手机号中间4位
			StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
			commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
		}
		result.setHasMore(page.getCurrentPage() < page.getTotalPage());
		return result;
	}
}
