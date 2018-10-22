package org;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.http.codec.ServerCodecConfigurer.ServerDefaultCodecs;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForTotalCount;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule ;
 
public class TestMain {

	public static void main(String[] args) throws Throwable {
		
		SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler scheduler = sf.getScheduler();
		
		JobDetail _jobDetails = newJob(TestJob.class)
				//.withIdentity("testJob","")
				.withIdentity("job1", "group1")
				.build();
		
		Trigger triger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
			      .startNow()
			      .withSchedule(simpleSchedule()
			              .withIntervalInSeconds(10)
			              .repeatForever())
			      .build();
				/*.withIdentity("trigger1", "group1")
				//.forJob(_jobDetails)
				//.startAt(futerdate)
				.startNow()
				.withPriority(Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)
				//.with
				.withSchedule(simpleSchedule()
						.repeatSecondlyForTotalCount(5, 10))
				.build();*/
		
		 scheduler.scheduleJob(_jobDetails, triger);
		/*Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.scheduleJob(_jobDetails, triger);*/
		scheduler.start();
		
		/*Trigger trigger = newTrigger() 
	             .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
	             .withSchedule(withIntervalInDays(3))
	             .startAt(futureDate(10, MINUTES))
	             .build();*/

	}

}
