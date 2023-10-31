package com.fx21044.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx21044.dto.ClinicDTO;
import com.fx21044.dto.DoctorName;
import com.fx21044.dto.SpecializationDTO;
import com.fx21044.dto.SpecializationName;
import com.fx21044.entity.Clinic;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Specialization;
import com.fx21044.repositories.ClinicRepository;

@Service
public class ClinicServiceImpl implements ClinicService{
	
	@Autowired
	ClinicRepository clinicRepository;
	
	//Tìm tất cả phòng khám
	@Override
	public List<ClinicDTO> findAll() {
		List<Clinic> clinics = clinicRepository.findAll();
		
		List<ClinicDTO> result = new ArrayList<>();
		
		for(int i = 0; i < clinics.size();i++) {
			
			ClinicDTO clinicDTO = new ClinicDTO();
			
			clinicDTO.setId(clinics.get(i).getId());
			clinicDTO.setName(clinics.get(i).getName());
			clinicDTO.setAddress(clinics.get(i).getAddress());
			clinicDTO.setPhone(clinics.get(i).getPhone());
			clinicDTO.setWorkTime(clinics.get(i).getWorkTime());
			List<Doctor> doctors = clinics.get(i).getDoctors();
			Set<Specialization> specializations = clinics.get(i).getSpecializations();
			
			List<Specialization> listSpe = new ArrayList<>(specializations);
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			List<SpecializationName> specializationNames = new ArrayList<SpecializationName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			for(int k = 0; k < listSpe.size();k++) {
				SpecializationName specializationName = new SpecializationName();
				specializationName.setSpeciazlizationName(listSpe.get(k).getName());
				specializationNames.add(specializationName);
			}
			
			clinicDTO.setDoctorNames(doctorNames);
			clinicDTO.setSpecializationNames(specializationNames);
			
			result.add(clinicDTO);
		}
		
		return result;
	}
	
	//Top phòng khám
	@Override
	public List<ClinicDTO> top4Clinic() {
		List<Clinic> clinics = clinicRepository.top4Clinic();
		
		List<ClinicDTO> result = new ArrayList<>();
		
		for(int i = 0; i < clinics.size();i++) {
			
			ClinicDTO clinicDTO = new ClinicDTO();
			
			clinicDTO.setId(clinics.get(i).getId());
			clinicDTO.setName(clinics.get(i).getName());
			clinicDTO.setAddress(clinics.get(i).getAddress());
			clinicDTO.setPhone(clinics.get(i).getPhone());
			clinicDTO.setWorkTime(clinics.get(i).getWorkTime());
			List<Doctor> doctors = clinics.get(i).getDoctors();
			Set<Specialization> specializations = clinics.get(i).getSpecializations();
			
			List<Specialization> listSpe = new ArrayList<>(specializations);
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			List<SpecializationName> specializationNames = new ArrayList<SpecializationName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			for(int k = 0; k < listSpe.size();k++) {
				SpecializationName specializationName = new SpecializationName();
				specializationName.setSpeciazlizationName(listSpe.get(k).getName());
				specializationNames.add(specializationName);
			}
			
			clinicDTO.setDoctorNames(doctorNames);
			clinicDTO.setSpecializationNames(specializationNames);
			
			result.add(clinicDTO);
		}
		
		return result;
	}
	
	//Tìm kiếm chung
	@Override
	public List<ClinicDTO> searchClinic(String keyword) {
		List<Clinic> clinics = clinicRepository.searchClinics(keyword);
		
		List<ClinicDTO> result = new ArrayList<>();
		
		for(int i = 0; i < clinics.size();i++) {
			
			ClinicDTO clinicDTO = new ClinicDTO();
			
			clinicDTO.setId(clinics.get(i).getId());
			clinicDTO.setName(clinics.get(i).getName());
			clinicDTO.setAddress(clinics.get(i).getAddress());
			clinicDTO.setPhone(clinics.get(i).getPhone());
			clinicDTO.setWorkTime(clinics.get(i).getWorkTime());
			List<Doctor> doctors = clinics.get(i).getDoctors();
			Set<Specialization> specializations = clinics.get(i).getSpecializations();
			
			List<Specialization> listSpe = new ArrayList<>(specializations);
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			List<SpecializationName> specializationNames = new ArrayList<SpecializationName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			for(int k = 0; k < listSpe.size();k++) {
				SpecializationName specializationName = new SpecializationName();
				specializationName.setSpeciazlizationName(listSpe.get(k).getName());
				specializationNames.add(specializationName);
			}
			
			clinicDTO.setDoctorNames(doctorNames);
			clinicDTO.setSpecializationNames(specializationNames);
			
			result.add(clinicDTO);
		}
		
		return result;
	}
	
	//Tìm phòng khám bằng chuyên khoa
	@Override
	public List<ClinicDTO> searchClinicsBySpecialization(String query) {
		List<Clinic> clinics = clinicRepository.searchClinicsBySpecialization(query);
		
		List<ClinicDTO> result = new ArrayList<>();
		
		for(int i = 0; i < clinics.size();i++) {
			
			ClinicDTO clinicDTO = new ClinicDTO();
			
			clinicDTO.setId(clinics.get(i).getId());
			clinicDTO.setName(clinics.get(i).getName());
			clinicDTO.setAddress(clinics.get(i).getAddress());
			clinicDTO.setPhone(clinics.get(i).getPhone());
			clinicDTO.setWorkTime(clinics.get(i).getWorkTime());
			List<Doctor> doctors = clinics.get(i).getDoctors();
			Set<Specialization> specializations = clinics.get(i).getSpecializations();
			
			List<Specialization> listSpe = new ArrayList<>(specializations);
			
			List<DoctorName> doctorNames = new ArrayList<DoctorName>();
			
			List<SpecializationName> specializationNames = new ArrayList<SpecializationName>();
			
			for(int j = 0; j < doctors.size(); j++) {
				DoctorName name = new DoctorName();
				name.setName(doctors.get(j).getUser().getName());
				doctorNames.add(name);
			}
			
			for(int k = 0; k < listSpe.size();k++) {
				SpecializationName specializationName = new SpecializationName();
				specializationName.setSpeciazlizationName(listSpe.get(k).getName());
				specializationNames.add(specializationName);
			}
			
			clinicDTO.setDoctorNames(doctorNames);
			clinicDTO.setSpecializationNames(specializationNames);
			
			result.add(clinicDTO);
		}
		
		return result;
	}

}
