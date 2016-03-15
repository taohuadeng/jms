package com.thd.jm.quartz;

import com.thd.jm.domain.Job;
import com.thd.jm.jms.JobMessageProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.dao.DuplicateKeyException;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;


public class QuartzExecute implements org.quartz.Job {


    JobMessageProducer jobMessageProducer;
    Log log = LogFactory.getLog(QuartzExecute.class);

    @Override
    public void execute(JobExecutionContext je) throws JobExecutionException {
        Job job = (Job) je.getJobDetail().getJobDataMap().get("job");

        jobMessageProducer = (JobMessageProducer) je.getJobDetail().getJobDataMap().get("jobMessageProducer");

        log.info(JmConstants.LOG_SIGN + "execute JobName：" + job.getJobName());
        //检查该任务是否可执行
        if (checkIsExecutable(job)) {
            try {
                //更新定时任务状态
                //String jobLogId = jobService.updateJobInfo(job, JmConstants.EXECUTE, null, null);

                //获取当前定时任务这次执行的日志
                //JobLog jobLog = jobLogService.getJobLogByJobLogId(jobLogId);
                try {


                    jobMessageProducer.jobMessageSend(job);
                } catch (JMSException e) {
                    log.info("error" + JmConstants.LOG_SIGN + "execute JobName：" + job.getJobName());
                    log.error(e.getMessage(), e);
                }
                log.info(JmConstants.LOG_SIGN + "execute finished JobName：" + job.getJobName() + " counter:");

                if (job.getIsCycle()) {
                    //如果为周期性任务则更新计数器
                    processJobExecuteCounter(job);
                } else {
                    //如果为非周期性任务则将任务从quartz中移除
                    removeJob(job, je);
                }
            } catch (DuplicateKeyException e) {
                log.error(JmConstants.LOG_SIGN + "DuplicateKeyException ...JobName：" + job.getJobName() + " counter:");
                //如果已经被其它JM处理过则将计数器更新准备进行下次处理
                processJobExecuteCounter(job);
            }

        } else {
            log.info(JmConstants.LOG_SIGN + "other JM executed... JobName：" + job.getJobName());
        }
    }

    /**
     * 将任务从quartz中移除
     *
     * @param job 定时任务对象
     * @param je  定时任务容器
     * @author ZHANG Nan
     */
    private void removeJob(Job job, JobExecutionContext je) {
        try {
            je.getScheduler().unscheduleJob(je.getTrigger().getName(), je.getTrigger().getGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新计数器
     *
     * @param job
     * @author ZHANG Nan
     */
    private void processJobExecuteCounter(Job job) {
        String yongdaoIp = null;
        if (JmConstants.DEFAULT_CORPCODE.equals(job.getCorpCode())) {
            yongdaoIp = System.getenv("yongdaoIp");
        }
    }

    /**
     * 检查该任务是否可执行
     *
     * @param job
     * @return
     * @author ZHANG Nan
     */
    private boolean checkIsExecutable(Job job) {
        return true;
    }

}
