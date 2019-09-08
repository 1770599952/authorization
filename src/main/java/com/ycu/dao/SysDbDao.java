package com.ycu.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysDbDao {
    /**
     *  创建数据表
     * @param name 表名
     */
    void createUserLogTable(@Param("name")  String name);

    List<String> queryUserLogTableName();
}
