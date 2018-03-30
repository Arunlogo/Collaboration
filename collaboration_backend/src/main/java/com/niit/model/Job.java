package com.niit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	int id;
	@Column(nullable=false)
	private String jobtitle;
	private String jobdescription;
	private Date postedon;
	private String skillsrequired;
	private String yearsofexp;
	private String salary;
	private String location;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	
	public Date getPostedon() {
		return postedon;
	}
	public void setPostedon(Date postedon) {
		this.postedon = postedon;
	}
	public String getSkillsrequired() {
		return skillsrequired;
	}
	public void setSkillsrequired(String skillsrequired) {
		this.skillsrequired = skillsrequired;
	}
	public String getYearsofexp() {
		return yearsofexp;
	}
	public void setYearsofexp(String yearsofexp) {
		this.yearsofexp = yearsofexp;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
