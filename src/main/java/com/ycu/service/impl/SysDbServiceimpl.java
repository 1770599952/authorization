package com.ycu.service.impl;

import com.ycu.dao.SysDbDao;
import com.ycu.service.SysDbService;
import com.ycu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Yang on 2018/4/1.
 */
@Service("sysDbService")
public class SysDbServiceimpl implements SysDbService {

    @Autowired
    private SysDbDao sysDbDao;

    @Override
    public void createTable(String preName, Boolean sufTime) {
        if (sufTime) {
        	String tableName = preName + "_" + getSufName();
            sysDbDao.createUserLogTable(tableName);
        } else {
            sysDbDao.createUserLogTable(preName);
        }
    }

    @Override
    public List<String> queryUserLogTableName() {
        return sysDbDao.queryUserLogTableName();
    }

    /**
     * 获取名称后缀<br>
     * 格式: 20180401
     *
     * @return
     */
    private String getSufName() {
        Date d = new Date();
        String s = DateUtil.date2String(d, false);
        return s.replace("-", "");
    }

}
