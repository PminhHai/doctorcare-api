package com.fx21044.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.AcceptScheduleDTO;
import com.fx21044.dto.DoctorDTO;
import com.fx21044.dto.ScheduleDTO;
import com.fx21044.dto.UpdateInfoDTO;
import com.fx21044.dto.UserDoctorListDTO;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.User;
import com.fx21044.repositories.UserRepository;
import com.fx21044.response.MessageResponse;
import com.fx21044.service.DoctorService;
import com.fx21044.service.ScheduleService;
import com.fx21044.service.UserDetailsImpl;
import com.fx21044.service.UserService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	UserRepository userRepository;
	
	//Danh sách bệnh nhân của bác sĩ
	@GetMapping("/getUserDoctorList")
	public List<UserDoctorListDTO> getUserDoctorList(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<UserDoctorListDTO> result = userService.findUserByDoctorID(userDetails.getId());
		
		return result;
	}
	
	//Danh sách lịch khám của bác sĩ
	@GetMapping("/getAllSchedulesOfDoctor")
	public List<ScheduleDTO> getAllSchedulesOfDoctor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<Schedule> schedules = scheduleService.getScheduleByDoctorId(userDetails.getId());
		
		List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
		
		for(int i = 0; i < schedules.size(); i++) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			
			scheduleDTO.setDoctorName(schedules.get(i).getDoctor().getUser().getName());
			scheduleDTO.setUserName(schedules.get(i).getUser().getEmail());
			scheduleDTO.setClinicName(schedules.get(i).getClinic().getName());
			scheduleDTO.setSpecializationName(schedules.get(i).getSpecialization().getName());
			scheduleDTO.setDate(schedules.get(i).getDate());
			scheduleDTO.setTime(schedules.get(i).getTime());
			scheduleDTO.setDoctorConfirm(schedules.get(i).getDoctorConfirm());
			scheduleDTO.setReasonCancel(schedules.get(i).getReasonCancel());
			
			scheduleDTOs.add(scheduleDTO);
		}
		
		return scheduleDTOs;
	}
	
	//Xác nhận - hủy lịch khám
	@PutMapping("/acceptSchedule")
	public String acceptSchedule(@RequestBody AcceptScheduleDTO acceptScheduleDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		Schedule schedule = scheduleService.findById(acceptScheduleDTO.getScheduleId());
		
		
		if(userDetails.getId() != schedule.getDoctor().getId()) {
			return "403 - This is not schedule of this doctor";
		}
		
		if(schedule != null) {
			schedule.setDoctorConfirm(acceptScheduleDTO.getDoctorConfirm());
			schedule.setReasonCancel(acceptScheduleDTO.getReasonCancel());
		} 
		
		scheduleService.save(schedule);
		
		return "Action Success";
	}
	
	//Cập nhật thông tin
	@PutMapping("/updateInfo")
	public ResponseEntity<?> updateInfor(@Valid @RequestBody UpdateInfoDTO updateInfoDTO ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		Optional<User> userOptional = userRepository.findById(userDetails.getId());
		User user = new User();
		if(userOptional != null) {
			user = userOptional.get();
		}
		
		user.setName(updateInfoDTO.getName());
		user.setPhone(updateInfoDTO.getPhone());
		user.setAddress(updateInfoDTO.getAddress());
		user.setAvatar(updateInfoDTO.getAvatar());
		
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("Update success"));
	}
}
