package com.endava.cron;

import com.endava.entity.Employee;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("0", employeeList);
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger2", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
                .build();

        JobDetail job1 = JobBuilder.newJob(AddEmployeeCron.class)
                .usingJobData(jobDataMap)
                .withIdentity("job1", "group1")
                .build();
        JobDetail job2 = JobBuilder.newJob(ShowListCron.class)
                .usingJobData(jobDataMap)
                .withIdentity("job2", "group2")
                .build();
        try {
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(job1, trigger1);
            scheduler1.scheduleJob(job2, trigger2);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
