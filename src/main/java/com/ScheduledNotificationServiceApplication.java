package com;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.TestJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScheduledNotificationServiceApplication {
	private SchedulerFactory sf = new StdSchedulerFactory();
	public static void main(String[] args) {
		SpringApplication.run(ScheduledNotificationServiceApplication.class, args);
	}
		
	@GetMapping("/ss")
	public void startSheduler() throws Exception {
		
	    Scheduler scheduler = sf.getScheduler();
	    scheduler.start();
	}
	
	@GetMapping("/cs")
	public void cancelSheduler() throws SchedulerException {
		
	    Scheduler scheduler = sf.getScheduler();
	    
		scheduler.shutdown();
	}
	
	@GetMapping("/dj")
	public void deleteJob() throws SchedulerException{
		Scheduler scheduler = sf.getScheduler();	   
		JobDetail _jobDetails = newJob(TestJob.class)			
				.withIdentity("job1", "group1")
				.build();
	   
	}
	
/*	public void unscheduleJob(){
		Scheduler scheduler = sf.getScheduler();
		
		Trigger triger =TriggerBuilder.newTrigger()
		.withIdentity("","").build();
		scheduler.unscheduleJob(triggerKey("trigger1", "group1"));
	}*/
	
	@RequestMapping("/sh")
	public String sheduleJob(@RequestBody JobData jobRequest) throws Throwable {
		System.out.println(jobRequest+"&&&&&&&&&&&&&&&&&&&&&");
		sheduleJobs(jobRequest);
		return "200 ok";
	}
	
	public void sheduleJobs(JobData jobRequest) throws Throwable {
		
	    Scheduler scheduler = sf.getScheduler();
		
		JobDetail _jobDetails = newJob(TestJob.class)			
				.withIdentity(jobRequest.getJobName(), jobRequest.getGroupName())
				.usingJobData("dataKey", jobRequest.getMsgData())
				.build();
		
		Trigger triger = TriggerBuilder.newTrigger()
				.withIdentity(jobRequest.getTriggerName(), jobRequest.getGroupName())
			      .startNow()
			      .withSchedule(simpleSchedule()
			              .withIntervalInMilliseconds(1)
			              .repeatMinutelyForTotalCount(5))
			              //.repeatForever())
			      .build();
		
		scheduler.scheduleJob(_jobDetails, triger);
		scheduler.start();
	}
	
}
