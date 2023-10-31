package com.fx21044.dto;

import java.util.List;

public class ClinicDTO {
	
	private int id;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String workTime;
	
	private List<DoctorName> doctorNames;
	
	private List<SpecializationName> specializationNames;

	public ClinicDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public List<DoctorName> getDoctorNames() {
		return doctorNames;
	}

	public void setDoctorNames(List<DoctorName> doctorNames) {
		this.doctorNames = doctorNames;
	}

	public List<SpecializationName> getSpecializationNames() {
		return specializationNames;
	}

	public void setSpecializationNames(List<SpecializationName> specializationNames) {
		this.specializationNames = specializationNames;
	}
	
	
}
