package com.fx21044.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.fx21044.dto.ClinicDTO;

public interface ClinicService {
	List<ClinicDTO> findAll();
	
	List<ClinicDTO> top4Clinic();
	
	List<ClinicDTO> searchClinic(String keyword);
	
	List<ClinicDTO> searchClinicsBySpecialization(String query);
}
