package com.wj.QuartzTest;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

public class DumbJob2 implements Job {
    String jobSays;
    float myFloatValue;
    ArrayList state;

    public DumbJob2() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getMergedJobDataMap();

//        state.add(new Date());

        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
