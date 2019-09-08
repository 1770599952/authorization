package com.ycu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycu.bean.Action;
import com.ycu.bean.Group;
import com.ycu.bean.GroupAction;
import com.ycu.bean.GroupMenu;
import com.ycu.bean.Menu;
import com.ycu.dao.GroupActionDao;
import com.ycu.dao.GroupDao;
import com.ycu.dao.GroupMenuDao;
import com.ycu.dto.ActionDto;
import com.ycu.dto.GroupDto;
import com.ycu.dto.MenuDto;
import com.ycu.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;
	@Autowired
	private GroupMenuDao groupMenuDao;
	@Autowired
	private GroupActionDao groupActionDao;
	
	@Override
	public List<GroupDto> getList() {
		List<GroupDto> result = new ArrayList<>();
		List<Group> groupList = groupDao.select(new Group());
		for (Group group : groupList) {
			GroupDto groupDto = new GroupDto();
			result.add(groupDto);
			BeanUtils.copyProperties(group, groupDto);
			groupDto.setpId(0);
		}
		return result;
	}

	@Override
	public boolean add(GroupDto groupDto) {
		Group group = new Group();
		BeanUtils.copyProperties(groupDto, group);
		return groupDao.insert(group) == 1;
	}

	@Override
	public GroupDto getById(Long id) {
		GroupDto groupDto = new GroupDto();
		Group group = groupDao.selectById(id);
		BeanUtils.copyProperties(group, groupDto);
		return groupDto;
	}

	@Override
	public boolean modify(GroupDto groupDto) {
		Group group = new Group();
		BeanUtils.copyProperties(groupDto, group);
		return groupDao.update(group) == 1;
	}

	@Override
	public boolean remove(Long id) {
		return groupDao.delete(id) == 1;
	}

	@Override
	public GroupDto getByIdWithMenuAction(Long id) {
		GroupDto result = new GroupDto();
		List<MenuDto> menuDtoList = new ArrayList<>();
		List<ActionDto> actionDtoList = new ArrayList<>();
		result.setMenuDtoList(menuDtoList);
		result.setActionDtoList(actionDtoList);
		
		Group group = groupDao.selectMenuListById(id);
		if(group != null) {
			BeanUtils.copyProperties(group, result);
			for(Menu menu : group.getMenuList()) {
				MenuDto menuDto = new MenuDto();
				menuDtoList.add(menuDto);
				BeanUtils.copyProperties(menu, menuDto);
			}
			
			for(Action action : group.getActionList()) {
				ActionDto actionDto = new ActionDto();
				actionDtoList.add(actionDto);
				BeanUtils.copyProperties(action, actionDto);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public boolean assignMenu(GroupDto groupDto) {
		// 删除原来为用户组分配的权限和菜单
		groupMenuDao.deleteByGroupId(groupDto.getId());
		groupActionDao.deleteByGroupId(groupDto.getId());
		// 保存为用户组分配的菜单
		if(groupDto.getMenuIdList() != null && groupDto.getMenuIdList().size() > 0) {
			List<GroupMenu> list = new ArrayList<>();
			for(Long menuId : groupDto.getMenuIdList()) {
				if(menuId != null) {
					GroupMenu groupMenu = new GroupMenu();
					groupMenu.setGroupId(groupDto.getId());
					groupMenu.setMenuId(menuId);
					list.add(groupMenu);
				}
			}
			groupMenuDao.insertBatch(list);
		}
		// 保存为用户组分配的动作
		if(groupDto.getActionIdList() != null && groupDto.getActionIdList().size() > 0) {
			List<GroupAction> list = new ArrayList<>();
			for(Long actionId : groupDto.getActionIdList()) {
				if(actionId != null) {
					GroupAction groupAction = new GroupAction();
					groupAction.setGroupId(groupDto.getId());
					groupAction.setActionId(actionId);
					list.add(groupAction);
				}
			}
			groupActionDao.insertBatch(list);
		}
		return true;
	}

}