package com.thd.jm.quartz;

import com.thd.jm.domain.*;
import com.thd.jm.domain.Job;
import com.thd.jm.jms.JobMessageProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

public class QuartzJobTrigger {
    /**
     * 定时器工厂
     */
    @Resource
    public SchedulerFactoryBean schedulerFactory;
    /**
     * 定时任务发送消息类
     */
    public JobMessageProducer jobMessageProducer;
    /**
     * 初始化定时任务名称
     */
    public String initTriggerName;
    /**
     * 初始化定时任务job名称
     */
    public String initJobDetailName;
    /**
     * 向数据库获取新列表周期
     */
    public String cycleUpdateTime;

    Log log = LogFactory.getLog(QuartzJobTrigger.class);

    public void initJobTrigger() throws Exception {
        System.out.println(new Date() + "" + 123 + "begin");
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
//            try {
//                //线程停止1秒，避免quartz在1秒内重复调用
//                Thread.sleep(1000);
//                //设置quartz每隔cycleUpdateTime段时间重新执行一次
//                scheduler.unscheduleJob(initTriggerName, Scheduler.DEFAULT_GROUP);
//                CronTrigger cronTrigger = new CronTrigger(initTriggerName, Scheduler.DEFAULT_GROUP, initJobDetailName, Scheduler.DEFAULT_GROUP, cycleUpdateTime);
//                scheduler.scheduleJob(cronTrigger);
//            } catch (ParseException e) {
//                log.error(e.getMessage(), e);
//            } catch (InterruptedException e) {
//                log.error(e.getMessage(), e);
//            }



            Job job = new Job();
            job.setJobId("jobId");
            job.setJobName("Job name");
            job.setFunctionCode("FUNCTION_CODE");
            job.setFunctionName("拉登测试");
            job.setIsCycle(false);
            job.setCronExpression("0 0/1 * * * ?");
            JobDetail jobDetail = new JobDetail(JmConstants.JOB + JmConstants.JMS_JOIN_SIGN + job.getJobId(), Scheduler.DEFAULT_GROUP, QuartzExecute.class);
            // 设置定时任务参数
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("job", job);
            jobDataMap.put("jobMessageProducer", jobMessageProducer);

            jobDetail.setJobDataMap(jobDataMap);
            // 新建触发器，触发器为默认的Scheduler.DEFAULT_GROUP
            CronTrigger cronTrigger = new CronTrigger(JmConstants.TRIGGER + JmConstants.JMS_JOIN_SIGN + job.getJobId(), Scheduler.DEFAULT_GROUP);
            try {
                cronTrigger.setCronExpression(job.getCronExpression());

                Trigger trigger = scheduler.getTrigger(JmConstants.TRIGGER + JmConstants.JMS_JOIN_SIGN + job.getJobId(), Scheduler.DEFAULT_GROUP);
                if (trigger == null) {
                    // 启动新增定时器任务
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                } else {
                    //如果job已经存在 则删除老的 并加入新的job
                    scheduler.unscheduleJob(JmConstants.TRIGGER + JmConstants.JMS_JOIN_SIGN + job.getJobId(), Scheduler.DEFAULT_GROUP);
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // 任务启动
            scheduler.start();

            //打印quartz队列信息
            printSchedulerList();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void printSchedulerList() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        String jobNames[] = scheduler.getJobNames(Scheduler.DEFAULT_GROUP);
        for (String jobName : jobNames) {
            JobDetail jobDetail = scheduler.getJobDetail(jobName, Scheduler.DEFAULT_GROUP);

            StringBuffer logInfo = new StringBuffer();
            logInfo.append("scheduler initJobTrigger jobName.id in:" + jobName);

            if (jobDetail != null) {
                Job job = (Job) jobDetail.getJobDataMap().get("job");
                if (job != null) {
                    //logInfo.append(" name:" + job.getJobName());
                }
            }
            log.info(logInfo);
        }
    }

    public JobMessageProducer getJobMessageProducer() {
        return jobMessageProducer;
    }

    public void setJobMessageProducer(JobMessageProducer jobMessageProducer) {
        this.jobMessageProducer = jobMessageProducer;
    }

    public SchedulerFactoryBean getSchedulerFactory() {
        return schedulerFactory;
    }

    public void setSchedulerFactory(SchedulerFactoryBean schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    public String getInitTriggerName() {
        return initTriggerName;
    }

    public void setInitTriggerName(String initTriggerName) {
        this.initTriggerName = initTriggerName;
    }

    public String getCycleUpdateTime() {
        return cycleUpdateTime;
    }

    public void setCycleUpdateTime(String cycleUpdateTime) {
        this.cycleUpdateTime = cycleUpdateTime;
    }

    public String getInitJobDetailName() {
        return initJobDetailName;
    }

    public void setInitJobDetailName(String initJobDetailName) {
        this.initJobDetailName = initJobDetailName;
    }
}
