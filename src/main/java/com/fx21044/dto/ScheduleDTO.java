package com.fx21044.dto;

import java.util.Date;

public class ScheduleDTO {
	private String doctorName;
	
	private String userName;
	
	private String clinicName;
	
	private String specializationName;
	
	private Date date;
	
	private String time;
	
	private int doctorConfirm;
	
	private String reasonCancel;

	public ScheduleDTO() {
		super();
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getDoctorConfirm() {
		return doctorConfirm;
	}

	public void setDoctorConfirm(int doctorConfirm) {
		this.doctorConfirm = doctorConfirm;
	}

	public String getReasonCancel() {
		return reasonCancel;
	}

	public void setReasonCancel(String reasonCancel) {
		this.reasonCancel = reasonCancel;
	}
	
}
