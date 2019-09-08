package com.ycu.service;

import com.github.pagehelper.PageInfo;
import com.ycu.bean.SysUserLog;
import com.ycu.dto.SysUserLogQuery;

/**
 * Created by Yang on 2018/4/1.
 */
public interface SysUserLogService {

    /**
     * 添加系统用户日志
     *
     * @param sysUserLog 日志信息
     */
    void addUserLog(SysUserLog sysUserLog);

    /**
     * 分页获取日志列表
     *
     * @param query  查询参数
     * @param start  页数
     * @param length 每页个数
     * @return
     */
    PageInfo queryPage(SysUserLogQuery query, Integer start, Integer length) throws Exception;
}
