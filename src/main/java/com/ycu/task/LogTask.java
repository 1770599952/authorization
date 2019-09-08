package com.ycu.task;

import com.ycu.service.SysDbService;
import com.ycu.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Yang on 2018/4/2.
 */
@Component("logTask")
public class LogTask {

    private static final Logger log = LoggerFactory.getLogger(LogTask.class);
    /**
     * 默认表名
     */
    private static String tableName = "sys_user_log";

    public void sysUserLogTable(){
        try {
            //创建表
            SysDbService dbService = (SysDbService) SpringUtil.getBean("sysDbService");
            dbService.createTable(tableName, true);
            log.info("创建表" + tableName + "成功");
            //更新系统用户日志表表缓存 SysCacheUtil.flushSysUserLogTableName();
        } catch (Exception e) {
            log.error("创建表" + tableName + "失败");
            sysUserLogTable();
        }
    }
}
