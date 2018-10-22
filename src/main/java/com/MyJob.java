package com;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements org.quartz.Job {
	private static Logger _log = LoggerFactory.getLogger(MyJob.class);
    public MyJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        
    	_log.info("Hello World! - " + new Date());
    	System.err.println("Hello World!  MyJob is executing.");
        
    }
}
