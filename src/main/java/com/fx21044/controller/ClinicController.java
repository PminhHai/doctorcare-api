package com.fx21044.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.ClinicDTO;
import com.fx21044.service.ClinicService;

@RestController
@RequestMapping("/api")
public class ClinicController {
	
	@Autowired
	ClinicService clinicService;
	
	//Danh sách phòng khám
	@GetMapping("/allClinics")
	public List<ClinicDTO> getAllClinics() {
		
		return clinicService.findAll();
	}
	
	//Phòng khám nổi bật
	@GetMapping("/getTopClinics")
	public List<ClinicDTO> getTopClinic() {
		return clinicService.top4Clinic();
	}
	
	
}
