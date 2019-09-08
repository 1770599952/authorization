package com.ycu.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Yang on 2018/4/1.
 */
public class SysUserLogQuery {

    /**组织id*/
    private String organizationId;
    /**组织集合*/
    private List<String> organizationIds;
    /**开始时间*/
    private Date startTime;
    /**结束时间*/
    private Date endTime;
    /**用户名*/
    private String username;
    /**模块名称*/
    private String moduleName;
    
    public String getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    public List<String> getOrganizationIds() {
        return organizationIds;
    }
    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

}
