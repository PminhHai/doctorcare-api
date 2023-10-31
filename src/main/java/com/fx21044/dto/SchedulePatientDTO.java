package com.fx21044.dto;

import java.sql.Time;
import java.util.Date;

import com.fx21044.model.JwtResponse;

public class SchedulePatientDTO {
	
	private int scheduleId;
	
	private Date date;
	
	private String time;
	
	private String doctorName;
	
	private String clinicName;
	
	private String specializationName;

	public SchedulePatientDTO() {
		
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	
}
