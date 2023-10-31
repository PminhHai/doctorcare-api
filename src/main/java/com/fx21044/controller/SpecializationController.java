package com.fx21044.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.SpecializationDTO;
import com.fx21044.entity.Specialization;
import com.fx21044.service.SpecializationService;

@RestController
@RequestMapping("/api")
public class SpecializationController {
	
	@Autowired
	private SpecializationService specializationService;
	
	//Danh sách chuyên khoa
	@GetMapping("/specializations")
	public List<SpecializationDTO> getAllSpecializations(){
		return specializationService.findAll();
	}
	
	//Top chuyên khoa
	@GetMapping("/top4Specializations")
	public List<SpecializationDTO> topFourSpecializations(){
		return specializationService.top4Specializations();
	}
}
