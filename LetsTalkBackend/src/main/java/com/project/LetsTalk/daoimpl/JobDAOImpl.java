package com.project.LetsTalk.daoimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.LetsTalk.dao.JobDAO;
import com.project.LetsTalk.dao.UserDAO;
import com.project.LetsTalk.model.Job;
import com.project.LetsTalk.model.JobApplication;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private Job job;
	
//****************************************get Max Job Id*******************************************************
	private int getMaxJobID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(jobid) from Job")
					.uniqueResult();
			System.out.println("maxvalue "+maxValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
	
//*******************************************save Job*********************************************************
	public boolean saveJob(Job job) {
		try {
			job.setJobid(getMaxJobID() + 1);
			job.setDateOfPost(new Date());
			job.setJobStatus("OPEN");
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
//******************************************close Job*********************************************************
	public boolean closeJob(int jobid) {
		try {
			job=jobDAO.getJob(jobid);
			job.setJobStatus("CLOSE");
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
//******************************************open Job**********************************************************
	public boolean openJob(int jobid) {
		try {
			job=jobDAO.getJob(jobid);
			job.setJobStatus("CLOSE");
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
//***************************************update job *********************************************************
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

//**************************************get Job by job Id******************************************************
	public Job getJob(int jobid) {
		return (Job) sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid))
				.uniqueResult();
	}
	public List<Job> jobList() {
		return	sessionFactory.getCurrentSession().createQuery("from Job").list();
	}

//**************************************Job List by jobStatus************************************************
	public List<Job> jobList(char jobstatus) {
		return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobstatus", jobstatus))
				.list();
	}
	
//***********************************get max job application id*******************************************
	private int getMaxJobapplicationID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession()
					.createQuery("select max(jobappid) from JobApplication").uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}

//************************************is job Opened**************************************************
	public boolean isJobOpened(int jobid) {
		Job job = (Job) sessionFactory.getCurrentSession().createCriteria(Job.class)
				.add(Restrictions.eq("jobid", jobid)).uniqueResult();
		if (job != null && job.getJobStatus().equals("OPEN")) {
			return true;
		}
		return false;
	}
	
//************************************save Job application*********************************************
	public boolean saveJobApplication(JobApplication jobApplication) {
		try {
			jobApplication.setJobappid(getMaxJobapplicationID() + 1);
			jobApplication.setJobappstatus('N');
			jobApplication.setApplied_date(new Date());
			if (jobApplication.getReason() == null || jobApplication.getReason() == " ")
			{
				jobApplication.setReason("Interested");
			}
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
//***********************************update Job application*******************************************
	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
//***********************************job application list by JobId**************************************
	public List<JobApplication> jobApplicationlist(int jobid) {
		return sessionFactory.getCurrentSession().createCriteria(JobApplication.class)
				.add(Restrictions.eq("jobid", jobid)).list();
	}
	
//********************************job Application List by jobId and jobSatus**********************************************
	public List<JobApplication> jobApplicationlist(int jobid, char jobstatus) {
		return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobstatus", jobstatus))
				.add(Restrictions.eq("jobid", jobid)).list();
	}
	
//***********************************is job already applied***************************************
	public boolean isJobAlreadyApplied(String email, int jobid) {
		JobApplication jobApplication = (JobApplication) sessionFactory.getCurrentSession()
				.createCriteria(JobApplication.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("jobid", jobid)).uniqueResult();
		if (jobApplication == null) {
			return false;
		}
		return true;
	}

//********************************get Job Application*************************************
	public JobApplication getJobApplication(int jobAppId)
	{
		return (JobApplication) sessionFactory.getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("jobappid", jobAppId))
				.uniqueResult();
	}	
	public List<JobApplication> jobApplicationList(String email) {
		 List<JobApplication> jobApplication = (List<JobApplication>)sessionFactory.getCurrentSession().createCriteria(JobApplication.class)
				.add(Restrictions.eq("email", email)).list();

		return jobApplication;

	}
	
//*************************************delete job **********************************************
	public boolean deleteJob(int jobid) {
		try {
			sessionFactory.getCurrentSession().delete(getJob(jobid));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

//*********************************delete Job Application**************************
	public boolean deleteJobApplication(int jobAppid) {
		try {
			sessionFactory.getCurrentSession().delete(getJobApplication(jobAppid));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}