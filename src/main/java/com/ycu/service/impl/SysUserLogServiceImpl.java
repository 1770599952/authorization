package com.ycu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycu.bean.SysUserLog;
import com.ycu.dao.SysUserLogDao;
import com.ycu.dto.SysUserLogQuery;
import com.ycu.service.SysDbService;
import com.ycu.service.SysUserLogService;
import com.ycu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Yang on 2018/4/1.
 */
@Service("sysUserLogService")
public class SysUserLogServiceImpl implements SysUserLogService {

	@Autowired
	private SysUserLogDao userLogMapper;
	@Autowired
	private SysDbService dbService;

	/**
	 * sys_user_log
	 */
	private static String preTableName = "sys_user_log";

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void addUserLog(SysUserLog sysUserLog) {
		try {
			userLogMapper.insertToTable(getUserLogTableName(), sysUserLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PageInfo queryPage(SysUserLogQuery query, Integer start, Integer length) throws Exception {
		// 1.判断开始结束日期是否为空
		if (query.getStartTime() == null || query.getEndTime() == null) {
			throw new Exception("开始、结束时间不能为空");
		}
		// 从缓存中读取系统数据库中的日志表表名
		// List<String> userLogTableName = (List<String>)
		// SysCacheUtil.getSysUserLogTableName();
		List<String> userLogTableName = null;
		// TODO 需要缓存的解决
		// 检查系统用户日志表名缓存是否存在，不存在则查询
		if (userLogTableName == null) {
			userLogTableName = dbService.queryUserLogTableName();
		}
		String logTable = "";
		if (userLogTableName == null ? true : userLogTableName.size() > 0) {
			// 标记是否比较开始时间
			Boolean isStart = true;
			for (int i = 0; i < userLogTableName.size(); i++) {
				if (isStart) {
					// 如果表创建时间与开始时间时间同年同月 则使循环体往下执行
					if (DateUtil.equals(changeToDate(userLogTableName.get(i)), query.getStartTime())
							|| DateUtil.countSecond(changeToDate(userLogTableName.get(i)), query.getStartTime()) >= 0) {
						logTable = " ( select * from " + preTableName;
						isStart = false;
					} else {
						continue;
					}
				}
				logTable = logTable + " union all " + " select * from " + userLogTableName.get(i);
				// 如果表创建时间与结束时间同年同月 则跳出循环
				if (DateUtil.equals(changeToDate(userLogTableName.get(i)), query.getEndTime())) {
					break;
				}
			}
			if (!isStart) {
				logTable = logTable + " ) ";
			} else {
				logTable = preTableName;
			}
		} else {
			logTable = preTableName;
		}
		// 分页计算
		start = start / length + 1;
		PageHelper.startPage(start, length);
		// List<SysUserLog> userLogs = userLogMapper.selectAll(query);
		List<SysUserLog> userLogs = userLogMapper.selectAllByTables(logTable, query);
		PageInfo pageInfo = new PageInfo(userLogs);
		return pageInfo;
	}

	/**
	 * 获取最新的日志表表名
	 *
	 * @return
	 */
	private String getUserLogTableName() {
		// 取用户日志表名集合
		// List<String> userLogTableName = SysCacheUtil.getSysUserLogTableName();
		List<String> userLogTableName = dbService.queryUserLogTableName();
		// TODO 缓存问题
		// 判断最新表名是否与该月同月，不同月则创建该月日志表
		if (userLogTableName == null ? true : userLogTableName.size() <= 0) {
			dbService.createTable("sys_user_log", true);
			// TODO
			// userLogTableName = SysCacheUtil.flushSysUserLogTableName();
		} else {
			// userLogTableName.size() > 0
			String tableName = userLogTableName.get(userLogTableName.size() - 1);
			if (!DateUtil.equals(changeToDate(tableName), new Date(System.currentTimeMillis()))) {
				// 不同月，则创建该月表，并更新日志缓存
				dbService.createTable("sys_user_log", true);
				// TODO userLogTableName = SysCacheUtil.flushSysUserLogTableName();
			}
		}
		if (userLogTableName == null ? true : userLogTableName.size() <= 0) {
			return preTableName;
		}

		// TODO 更新缓存
		// SysCacheUtil.setSysUserLogTableName(userLogTableName);
		return userLogTableName.get(userLogTableName.size() - 1);
	}

	/**
	 * 获取名称后缀<br>
	 * 格式: 20170301
	 *
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getSufName() {
		Date d = new Date();
		String s = DateUtil.date2String(d, false);
		s = s.replace("-", "");
		s = s.substring(0, s.length() - 2);
		s = s + "01";
		return s;
	}

	/**
	 * 计算日志表时间
	 *
	 * @param tabelName
	 *            表名
	 * @return
	 */
	private Date changeToDate(String tabelName) {
		int lastIndexOf = tabelName.lastIndexOf('_');
		if (lastIndexOf >= 0) {
			tabelName = tabelName.substring(lastIndexOf + 1);
			String strDate = tabelName.substring(0, 4) + "-" + tabelName.substring(4, 6) + "-"
					+ tabelName.substring(6, 8);
			return DateUtil.string2Date(strDate);
		} else {
			return null;
		}
	}

}
