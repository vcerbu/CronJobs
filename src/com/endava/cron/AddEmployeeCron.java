package com.endava.cron;

import com.endava.entity.Employee;
import org.quartz.*;

import java.time.LocalTime;
import java.util.*;

public class AddEmployeeCron implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Employee> employeeList = (List<Employee>) context.getJobDetail().getJobDataMap().get("0");
        String[] names = {"John", "Tom", "Sam", "Chris", "Anne"};
        String[] surNames = {"Castle", "Trump", "Jane", "Plum", "Merlin"};
        Random random = new Random();
        System.out.println("Add new employee: " + LocalTime.now());
        employeeList.add(new Employee(names[random.nextInt(names.length)], surNames[random.nextInt(names.length)], random.nextFloat() * (10000 - 2000) + 2000));
    }
}
