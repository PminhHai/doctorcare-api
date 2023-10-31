package com.fx21044.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "health_results")
public class HealthResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "pathology")
	private String pathology;
	
	@Column(name = "pathological_description")
	private String pathologicalDescription;
	
	@Column(name = "pill")
	private String pill;
	
	@Column(name = "feed_back")
	private String feedBack;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedules_id")
	private Schedule schedule;

	public HealthResult() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathology() {
		return pathology;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	public String getPathologicalDescription() {
		return pathologicalDescription;
	}

	public void setPathologicalDescription(String pathologicalDescription) {
		this.pathologicalDescription = pathologicalDescription;
	}

	public String getPill() {
		return pill;
	}

	public void setPill(String pill) {
		this.pill = pill;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
