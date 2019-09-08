package com.ycu.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ycu.bean.Member;
import com.ycu.bean.Orders;
import com.ycu.constant.CommentStateConst;
import com.ycu.dao.OrdersDao;
import com.ycu.dto.OrdersDto;
import com.ycu.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Resource
	private OrdersDao ordersDao;
	
	@Value("${businessImage.url}")
    private String businessImageUrl;

	@Override
	public boolean add(OrdersDto ordersDto) {
		Orders orders = new Orders();
		BeanUtils.copyProperties(ordersDto, orders);
		orders.setCommentState(CommentStateConst.NOT_COMMENT);
		ordersDao.insert(orders);
		return true;
	}

	@Override
	public OrdersDto getById(Long id) {
		OrdersDto result = new OrdersDto();
		Orders orders = ordersDao.selectById(id);
		BeanUtils.copyProperties(orders, result);
		return result;
	}

	@Override
	public List<OrdersDto> getListByMemberId(Long memberId) {
		List<OrdersDto> result = new ArrayList<OrdersDto>();
		Orders ordersForSelect = new Orders();
		ordersForSelect.setMemberId(memberId);
		List<Orders>  ordersList = ordersDao.select(ordersForSelect);
		for(Orders orders : ordersList) {
			OrdersDto ordersDto = new OrdersDto();
			result.add(ordersDto);
			BeanUtils.copyProperties(orders, ordersDto);
			ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
			ordersDto.setTitle(orders.getBusiness().getTitle());
			ordersDto.setCount(orders.getBusiness().getNumber());
		}
		return result;
	}

	@Override
	public List<OrdersDto> searchByPage(OrdersDto dto) {
		List<OrdersDto> result = new ArrayList<OrdersDto>();
		Orders condition = new Orders();
		BeanUtils.copyProperties(dto, condition);
		if(null == condition.getMember()) {
			condition.setMember(new Member());
		}
		List<Orders> orderList = ordersDao.selectByPage(condition);
		for (Orders order : orderList) {
			OrdersDto orderDtoTemp = new OrdersDto();
			result.add(orderDtoTemp);
			BeanUtils.copyProperties(order, orderDtoTemp);
		}
		return result;	
	}

	@Override
	public boolean remove(Long id) {
		int deleteRows = ordersDao.delete(id);
		return 1 == deleteRows ? true : false;
	}

}