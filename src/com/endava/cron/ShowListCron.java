package com.endava.cron;

import com.endava.entity.Employee;
import org.quartz.*;

import java.time.LocalTime;
import java.util.*;

public class ShowListCron implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Employee> employeeList = (List<Employee>) context.getJobDetail().getJobDataMap().get("0");
        System.out.println("Display employees: " + LocalTime.now());
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
