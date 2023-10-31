package com.fx21044.dto;

import java.util.List;

import com.fx21044.model.JwtResponse;

public class PatientDTO extends JwtResponse{
	
	private List<SchedulePatientDTO> schedulePatientDTOs;
	
	public PatientDTO() {
		super();
	}

	public PatientDTO(String accessToken, int id, String name, String email, String role) {
		super(accessToken, id, name, email, role);
	}

	public List<SchedulePatientDTO> getSchedulePatientDTOs() {
		return schedulePatientDTOs;
	}

	public void setSchedulePatientDTOs(List<SchedulePatientDTO> schedulePatientDTOs) {
		this.schedulePatientDTOs = schedulePatientDTOs;
	}
	
}
