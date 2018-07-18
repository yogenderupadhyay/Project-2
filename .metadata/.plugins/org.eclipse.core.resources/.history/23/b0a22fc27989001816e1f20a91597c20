package com.project.LetsTalk;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project.LetsTalk.dao.JobDAO;
import com.project.LetsTalk.model.Job;
import com.project.LetsTalk.model.JobApplication;

public class JobTestCases {
	
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static JobDAO jobDAO;

	@Autowired
	private static Job job;

	@Autowired
	private static JobApplication jobApplication;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.project.*");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
		jobApplication = (JobApplication) context.getBean("jobApplication");
	}

	@Ignore
	public void saveJobTestCase() {
		job.setJobDescription("This is junior Technical Manager job.");
		job.setNoOfOpenings(6);
		job.setJobQualification("B.Sc");
		job.setJobSalary(40000);
		job.setJobTitle("Junior Technical Manager");
		Assert.assertEquals("Save job test case", true, jobDAO.saveJob(job));
	}
	@Ignore
	public void deleteJobTestcase()
	{
		boolean actual=jobDAO.deleteJob(52);
		assertEquals("delete job test case", true, actual);
	}

	@Ignore
	public void updateJobTestCaseSuccess() {
		job = jobDAO.getJob(101);
		job.setJobStatus("OPEN");
		Assert.assertEquals(true, jobDAO.updateJob(job));
	}

	@Test
	public void getJobSuccessTestCase()
	{
		Assert.assertNotNull( jobDAO.getJob(101));
	}
	
	@Ignore
	public void getAllJobsTestCase()
	{
	List<Job> jobs = jobDAO.jobList();
	Assert.assertEquals(1,	jobs.size());
	}
	
	@Ignore
	public void closeJobTestCase()
	{
	job=	jobDAO.getJob(102);
	job.setJobStatus("CLOSE");
	 Assert.assertEquals(true ,jobDAO.updateJob(job));
	}
	
	@Test
	public void isJobOpendSuccessTestCase()
	{
	   Assert.assertEquals(true,	jobDAO.isJobOpened(101));
	}
	
	//========================================================================//
	//JOB Application related test cases

	@Ignore
	public void applyJobSuccessTestCase()
	{
		jobApplication.setEmail("yatin@LT.COM");
		jobApplication.setJobid(103);
 		Assert.assertEquals("apply job test case", true, jobDAO.saveJobApplication(jobApplication));
	}
	
	@Test
	public void applyJobFailureTestCase()
	{
		jobApplication.setEmail("jaya@123");
		jobApplication.setJobid(101);
		Assert.assertEquals(false ,jobDAO.saveJobApplication(jobApplication));
	}
	
	@Ignore 
	public void isJobAlreadyAppliedSuccessTestCase()
	{
		Assert.assertEquals(true ,jobDAO.isJobAlreadyApplied("yatin@LT.COM", 101));
	}
	
	@Ignore 
	public void isJobAlreadyAppliedFailureTestCase()
	{
		Assert.assertEquals(false ,jobDAO.isJobAlreadyApplied("yatin@LT.COM", 102));
	}

}
