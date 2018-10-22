package com;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyMainClass {

	public static void main(String[] args) throws SchedulerException {
		
		SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler scheduler = sf.getScheduler();
		
		JobDetail job = newJob(MyJob.class)
			      .withIdentity("job1", "group1")
			      .build();

			  // Trigger the job to run now, and then repeat every 40 seconds
			  Trigger trigger = newTrigger()
			      .withIdentity("trigger1", "group1")
			      .startNow()
			      .withSchedule(simpleSchedule()
			              .withIntervalInSeconds(40)
			              .repeatForever())
			      .build();

			  // Tell quartz to schedule the job using our trigger
			  scheduler.scheduleJob(job, trigger);

	}

}
