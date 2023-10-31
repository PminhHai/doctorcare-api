package com.fx21044.dto;

import java.util.List;

import com.fx21044.entity.Doctor;

public class SpecializationDTO {
	private int id;
	
	private String name;
	
	private String description;
	
	private List<DoctorName> doctors;

	public SpecializationDTO() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DoctorName> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorName> doctors) {
		this.doctors = doctors;
	}
	
}
