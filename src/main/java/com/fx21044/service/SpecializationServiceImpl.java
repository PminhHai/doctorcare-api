package com.fx21044.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx21044.dto.DoctorName;
import com.fx21044.dto.SpecializationDTO;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Specialization;
import com.fx21044.repositories.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements SpecializationService{
	
	@Autowired
	private SpecializationRepository specializationRepository;
	
	//Lấy danh sách chuyên khoa
	@Override
	public List<SpecializationDTO> findAll() {
		
		List<Specialization> specializations = specializationRepository.findAll();
		
		List<SpecializationDTO> result = new ArrayList<>();
		
		for(int i = 0; i < specializations.size();i++) {
			
			SpecializationDTO specializationDTO = new SpecializationDTO();
			
			specializationDTO.setId(specializations.get(i).getId());
			specializationDTO.setName(specializations.get(i).getName());
			specializationDTO.setDescription(specializations.get(i).getDescription());
			
			List<Doctor> doctors = specializations.get(i).getDoctors();
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			specializationDTO.setDoctors(doctorNames);
			
			result.add(specializationDTO);
		}
		
		return result;
	}

	//Top danh sách chuyên khoa
	@Override
	public List<SpecializationDTO> top4Specializations() {
		
		List<Specialization> specializations = specializationRepository.top4Specializations();
		
		List<SpecializationDTO> result = new ArrayList<>();
		
		for(int i = 0; i < specializations.size();i++) {
			
			SpecializationDTO specializationDTO = new SpecializationDTO();
			
			specializationDTO.setId(specializations.get(i).getId());
			specializationDTO.setName(specializations.get(i).getName());
			specializationDTO.setDescription(specializations.get(i).getDescription());
			
			List<Doctor> doctors = specializations.get(i).getDoctors();
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			specializationDTO.setDoctors(doctorNames);
			
			result.add(specializationDTO);
		}
		
		return result;
	}

	

}
