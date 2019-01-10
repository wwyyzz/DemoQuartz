package com.wj.QuartzTest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.examples.example9.Job1Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener implements JobListener {
    private static Logger _log = LoggerFactory.getLogger(MyListener.class);

    @Override
    public String getName() {
        return "myListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        _log.info("Job1Listener says: " + jobName + " Is about to be executed.");
        System.out.println("just a monent.....");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        _log.info("Job1Listener says: Job Execution was vetoed.");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String jobName = context.getJobDetail().getKey().getName();
        _log.info("Job1Listener says: " + jobName + " was executed.");
    }
}
