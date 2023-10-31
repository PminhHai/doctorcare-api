package com.fx21044.service;

import java.util.List;
import java.util.Optional;

import com.fx21044.dto.DoctorDTO;
import com.fx21044.entity.Doctor;

public interface DoctorService {
	List<DoctorDTO> getAllDoctor();
	
	Optional<Doctor> findDoctorByID(int id);
	
	Optional<Doctor> findDoctorByUserId(int id);
}
