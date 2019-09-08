package com.ycu.bean;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统用户日志
 */
public class SysUserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**日志id*/
    private String logId;
    /**用户id*/
    private String userId;
    /**模块名称*/
    private String moduleName;
    /**操作*/
    private String operate;
    /**时间*/
    private Date time;
    /**类名*/
    private String className;
    /**方法名*/
    private String methodName;
    /**传入参数*/
    private String params;
    /**操作ip*/
    private String ip;

    public String getLogId() {
        return logId;
    }
    public void setLogId(String logId) {
        this.logId = logId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    //扩展字段
    /**用户账号*/
    private String username;
    /**组织名称*/
    private String organizationName;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}