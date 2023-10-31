package com.fx21044.dto;

public class AcceptScheduleDTO {
	
	private int scheduleId;
	
	private int doctorConfirm;
	
	private String reasonCancel;

	public AcceptScheduleDTO() {
		super();
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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
