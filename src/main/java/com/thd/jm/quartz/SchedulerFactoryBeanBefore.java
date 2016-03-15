package com.thd.jm.quartz;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class SchedulerFactoryBeanBefore extends SchedulerFactoryBean {

    private String defaultCorpCode;

    /**
     * 在quartz启动初始化时将默认的default放入
     */
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }


    public String getDefaultCorpCode() {
        return defaultCorpCode;
    }

    public void setDefaultCorpCode(String defaultCorpCode) {
        this.defaultCorpCode = defaultCorpCode;
    }
}
