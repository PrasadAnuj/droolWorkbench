package org;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.MyJob;

public class TestJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(TestJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getJobDetail().getKey().getName());
		System.out.println(context.getJobDetail().getJobDataMap().getString("dataKey")+" : msgData");
		System.out.println("**********************test Job : "+new Date());		
	}
}
