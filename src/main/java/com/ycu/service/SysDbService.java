package com.ycu.service;

import java.util.List;

/**
 * Created by Yang on 2018/4/1.
 */
public interface SysDbService {
    /**
     * 创建数据表
     *
     * @param preName   名称前缀
     * @param isSufTime 是否显示 时间后缀
     */
    void createTable(String preName, Boolean isSufTime);

    /**
     * 查询系统数据库中日志数据表表名
     * */
    List<String> queryUserLogTableName();
}
