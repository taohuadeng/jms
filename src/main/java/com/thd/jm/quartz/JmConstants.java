package com.thd.jm.quartz;

/**
 * @author ZHANG Nan
 */
public class JmConstants {
	/**
	 * Job.execute_status字段状态 WAITING=等待执行、EXECUTE=执行中、FINISHED=执行完毕、FAILURE=执行失败
	 */
	public static final String WAITING="WAITING";
	public static final String EXECUTE="EXECUTE";

	public static final String FINISHED="FINISHED";
	public static final String FAILURE="FAILURE";


	/**
	 * quartz定时任务与触发器名称常量 JOB=定时任务名称前缀 、TRIGGER=触发器名称前缀、JMSJOINSING=JMS消息队列Queue名称连接符号、DEFAULTCORPCODE=默认启动quartz公司
	 */
	public static final String JOB="job";
	public static final String JOB_YONGDAO_SYMBOL="_";

	public static final String TRIGGER="trigger";
	public static final String JMS_JOIN_SIGN=".";
	public static final String DEFAULT_CORPCODE="default";
	public static final String EXPIRED_ERROR="Based on configured schedule, the given trigger will never fire.";
	public static final String QUARTZ_EXPRESSION_FORMAT="ss mm HH dd MM ? yyyy";
	public static final String LOG_SIGN="-------------------------";
	public static final String APPCODE="appCode";
}
