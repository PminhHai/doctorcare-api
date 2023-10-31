package com.fx21044.service;

import java.util.List;

import com.fx21044.dto.SpecializationDTO;

public interface SpecializationService {
	List<SpecializationDTO> findAll();
	
	List<SpecializationDTO> top4Specializations();
	
	
}
