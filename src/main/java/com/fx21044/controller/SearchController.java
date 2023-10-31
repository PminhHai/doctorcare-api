package com.fx21044.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.ClinicDTO;
import com.fx21044.service.ClinicService;

@RestController
@RequestMapping("/api")
public class SearchController {
	
	@Autowired
	ClinicService clinicService;
	
	//Tìm kiếm chung
	@GetMapping("/search")
    public ResponseEntity<List<ClinicDTO>> searchClinic(@RequestParam("query") String query){
        return ResponseEntity.ok(clinicService.searchClinic(query));
    }
	
	//Tìm bằng chuyên khoa
	@GetMapping("/searchBySpecialization")
    public ResponseEntity<List<ClinicDTO>> searchBySpecialization(@RequestParam("query") String query){
        return ResponseEntity.ok(clinicService.searchClinicsBySpecialization(query));
    }
}
