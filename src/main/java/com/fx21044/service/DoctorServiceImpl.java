package com.fx21044.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx21044.dto.DoctorDTO;
import com.fx21044.entity.Doctor;
import com.fx21044.repositories.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	
	//Lấy toàn bộ danh sách bác sĩ
	@Override
	public List<DoctorDTO> getAllDoctor() {
		
		List<Doctor> doctors = doctorRepository.findAll();
		
		List<DoctorDTO> doctorDTOs = new ArrayList<>();
		
		for(int i = 0; i < doctors.size();i++) {
			
			DoctorDTO doctorDTO = new DoctorDTO();
			
			doctorDTO.setId(doctors.get(i).getId());
			doctorDTO.setDoctorName(doctors.get(i).getUser().getName());
			doctorDTO.setClinicName(doctors.get(i).getClinic().getName());
			doctorDTO.setSpecializationName(doctors.get(i).getSpecialization().getName());
			
			doctorDTOs.add(doctorDTO);
		}
		
		
		return doctorDTOs;
	}
	
	//Tìm bác sĩ bằng id 
	@Override
	public Optional<Doctor> findDoctorByID(int id) {
		
		return doctorRepository.findById(id);
	}

	@Override
	public Optional<Doctor> findDoctorByUserId(int id) {
		
		return doctorRepository.findDoctorByUserId(id);
	}

}
