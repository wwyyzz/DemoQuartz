package com.wj.QuartzTest;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {
    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(DumbJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("jobSays","Hello World!")
                    .usingJobData("myFloatValue", 3.14F)
                    .build();

            JobDetail job2 = newJob(DumbJob2.class)
                    .withIdentity("job2", "group1")
                    .usingJobData("jobSays","Hello World!")
                    .usingJobData("myFloatValue", 3.14F)
                    .build();

            JobDetail job3 = newJob(DumbJob2.class)
                    .withIdentity("job3", "group1")
                    .usingJobData("jobSays","Hi World!")
                    .usingJobData("myFloatValue", 2.72F)
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();

            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();

            Trigger trigger3 = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2, trigger2);
            scheduler.scheduleJob(job3, trigger3);

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown(true);
        }catch (SchedulerException se){
            se.printStackTrace();
        }
        }
}
