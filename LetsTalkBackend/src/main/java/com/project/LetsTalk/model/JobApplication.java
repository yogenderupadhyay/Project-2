package com.project.LetsTalk.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "c_jobApplication")
public class JobApplication
{
	@Id
	private int jobappid;
	private String email;
	private int jobid;
	private Date applied_date;
	private char jobappstatus; /* N->New application, R->Rejected, A->Accepted, C->Call for interview, S->Selected */

	@Transient
	private String statusMessage;
	
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	private String reason;
	public int getJobappid() {

		return jobappid;

	}

	public void setJobappid(int jobappid) {

		this.jobappid = jobappid;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public int getJobid() {

		return jobid;

	}

	public void setJobid(int jobid) {

		this.jobid = jobid;

	}

	public Date getApplied_date() {

		return applied_date;

	}

	public void setApplied_date(Date applied_date) {

		this.applied_date = applied_date;

	}

	public char getJobappstatus() {

		return jobappstatus;

	}

	public void setJobappstatus(char jobappstatus) {

		this.jobappstatus = jobappstatus;

	}

	public String getReason() {

		return reason;

	}

	public void setReason(String reason) {

		this.reason = reason;

	}

}