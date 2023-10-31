package com.fx21044.dto;

import com.fx21044.model.JwtResponse;

public class DoctorLoginDTO extends JwtResponse{
	
	private String clinicName;
	
	private String specializationName;

	public DoctorLoginDTO() {
		
	}

	public DoctorLoginDTO(String accessToken, int id, String name, String email, String role) {
		super(accessToken, id, name, email, role);
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
