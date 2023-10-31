package com.fx21044.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.BookingScheduleDTO;
import com.fx21044.dto.UpdateInfoDTO;
import com.fx21044.dto.UserDTO;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.User;
import com.fx21044.repositories.UserRepository;
import com.fx21044.response.MessageResponse;
import com.fx21044.service.DoctorService;
import com.fx21044.service.ScheduleService;
import com.fx21044.service.UserDetailsImpl;
import com.fx21044.service.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private UserRepository userRepository;;
	
	//Đặt lịch
	@PostMapping("/bookingSchedule")
	public String bookingSchedule(@Valid @RequestBody BookingScheduleDTO bookingScheduleDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		User currentUser = userService.findByEmail(email).get();
		
		Optional<Doctor> doctorOptional = doctorService.findDoctorByID(bookingScheduleDTO.getDoctorId());
		Schedule schedule = new Schedule();
		if(!doctorOptional.isPresent()) {
			return "Doctor is not existed";
		}
		
		Doctor doctor = doctorOptional.get();
		
		schedule.setDoctor(doctor);
		schedule.setUser(currentUser);
		schedule.setClinic(doctor.getClinic());
		schedule.setSpecialization(doctor.getSpecialization());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date parsed;
		try {
			
			parsed = format.parse(bookingScheduleDTO.getDate());
			
			java.sql.Date date = new java.sql.Date(parsed.getTime());
			schedule.setDate(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		schedule.setTime(bookingScheduleDTO.getTime());
		
		schedule.setDoctorConfirm(0);
		
		scheduleService.save(schedule);
		
		return "Booking schedule successfully!";
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
