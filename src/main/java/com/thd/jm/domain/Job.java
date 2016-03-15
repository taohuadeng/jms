package com.thd.jm.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Job {
    /**
     * 主键
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 应用code
     */
    private String appCode;
    /**
     * 方法code
     */
    private String functionCode;
    /**
     * 任务参数
     */
    private String jobContext;
    /**
     * 任务执行表达式
     */
    private String cronExpression;
    /**
     * 是否为周期性任务
     */
    private Boolean isCycle;

    /**
     * 任务执行状态
     */
    private String executeStatus;

    /**
     * 公司code
     */
    private String corpCode;

    /**
     * 扩展字段
     */
    private Map<String, Object> extMap;

    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次修改人
     */
    private String lastModifyBy;
    /**
     * 最后一次修改时间
     */
    private Date lastModifyTime;

    /**
     * 操作时间
     */
    private Long optTime;

    /**
     * 来源id 用于其他应用自己识别对应的定时任务
     */
    private String sourceId;

    /**
     * 高级查询-应用名称
     */
    private String appName;

    /**
     * 高级查询-创建人
     */
    private String createName;

    /**
     * 高级查询-创建开始时间
     */
    private Date createTimeBegin;

    /**
     * 高级查询-创建结束时间
     */
    private Date createTimeEnd;

    /**
     * 简单查询-任务名称或应用名称
     */
    private String jobNameOrAppCode;

    /**
     * 查询结果-执行次数
     */
    private Integer thisExecuteCount;

    /**
     * 查询结果-行为动作
     */
    private String functionName;


    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getJobNameOrAppCode() {
        return jobNameOrAppCode;
    }

    public void setJobNameOrAppCode(String jobNameOrAppCode) {
        this.jobNameOrAppCode = jobNameOrAppCode;
    }

    public Integer getThisExecuteCount() {
        return thisExecuteCount;
    }

    public void setThisExecuteCount(Integer thisExecuteCount) {
        this.thisExecuteCount = thisExecuteCount;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getJobContext() {
        return jobContext;
    }

    public void setJobContext(String jobContext) {
        this.jobContext = jobContext;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getIsCycle() {
        return isCycle;
    }

    public void setIsCycle(Boolean isCycle) {
        this.isCycle = isCycle;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getOptTime() {
        return optTime;
    }

    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
