package com.ycu.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycu.bean.Action;
import com.ycu.controller.content.AdController;
import com.ycu.dao.ActionDao;
import com.ycu.dto.ActionDto;
import com.ycu.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {
	
	@Autowired
	private AdController ad;
	
	@Autowired
	private ActionDao actionDao;
	
	@Override
	public boolean add(ActionDto dto) {
		return actionDao.insert(dto) == 1;
	}

	@Override
	public boolean remove(Long id) {
		return actionDao.deleteById(id) == 1;
	}

	@Override
	public boolean modify(ActionDto dto) {
		Action action = new Action();
		BeanUtils.copyProperties(dto,action);
		return actionDao.update(action) == 1;
	}

	@Override
	public ActionDto getById(Long id) {
		ActionDto result = new ActionDto();
		Action action = actionDao.selectById(id);
		BeanUtils.copyProperties(action, result);
		return result;
	}
}
