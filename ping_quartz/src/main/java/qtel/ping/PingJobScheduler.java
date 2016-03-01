package qtel.ping;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import qtel.ping.job.PingJob;

/**
 * 
 * @author luigi
 *
 */
public class PingJobScheduler {
	
	public static void main(String[] args) {
		
		try {
			
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(PingJob.class)
			    .withIdentity("PingJob")
			    .build();
			
			// specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
			      .withSchedule(  
	                    SimpleScheduleBuilder.simpleSchedule()
	                    .withIntervalInSeconds(900)
	                    .repeatForever())  
                        .build();  
	    	
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
	    	sch.start();	    	
	    	sch.scheduleJob(job, trigger);		
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
