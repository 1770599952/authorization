package com.ycu.controller.interceptor;

import com.ycu.annotation.LogAnnotation;
import com.ycu.bean.SysUserLog;
import com.ycu.constant.SessionKeyConst;
import com.ycu.dto.UserDto;
import com.ycu.service.SysUserLogService;
import com.ycu.util.CommonUtil;
import com.ycu.util.IDUtils;
import com.ycu.util.JSONUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Yang on 2018/4/1. 日志拦截器
 */
@Component("logInterceptor")
public class LogInterceptor {

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private SysUserLogService userLogService;

	/**
	 * 方法正常完成后执行方法
	 * 
	 * @param point
	 */
	@SuppressWarnings("rawtypes")
	public void afterReturning(JoinPoint point) {
		try {
			UserDto userDto = null;
			try {
				userDto = (UserDto) session.getAttribute(SessionKeyConst.USER_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			SysUserLog userLog = new SysUserLog();
			String targetName = point.getTarget().getClass().getName();
			Class targetClass = Class.forName(targetName);
			String methodName = point.getSignature().getName();
			Method[] method = targetClass.getMethods();
			Object[] params = point.getArgs(); 			// 获得参数列表
			for (Method m : method) {
				if (m.getName().equals(methodName)) {
					Class[] tmpCs = m.getParameterTypes();
					if (tmpCs.length == params.length) {
						// 获取注解内容
						LogAnnotation logAnnotation = m.getAnnotation(LogAnnotation.class);
						if (logAnnotation != null) {
							// 写入参数
							if (params.length > 0) {
								// 使用json转换工具 将参数转为json串，以便存入数据库
								 userLog.setParams(Arrays.toString(params));
//								 System.out.println(JSONUtils.toJSONString(tmpCs[0].cast(params[0])));
							}
							// 获取模块名称
							String moduleName = logAnnotation.moduleName();
							// 获取操作名称
							String operate = logAnnotation.operate();
							userLog.setModuleName(moduleName);
							userLog.setOperate(operate);
							userLog.setClassName(targetName);
							userLog.setMethodName(methodName);
							userLog.setLogId(String.valueOf(IDUtils.genItemId()));
							userLog.setTime(new Date());
							userLog.setIp(CommonUtil.getIpAddr(request));
							if(null == userDto.getId()) {
								userLog.setUserId(String.valueOf(userDto.getId()));
							}
							// SysUserLogService userLogService = (SysUserLogService) SpringUtil.getBean("sysUserLogService");
							userLogService.addUserLog(userLog);
							break;
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}

}
