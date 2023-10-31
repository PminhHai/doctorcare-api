package com.fx21044.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fx21044.dto.CreateDoctorDTO;
import com.fx21044.dto.DoctorDTO;
import com.fx21044.dto.ScheduleDTO;
import com.fx21044.dto.UpdateInfoDTO;
import com.fx21044.dto.UserDTO;
import com.fx21044.entity.Clinic;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.Specialization;
import com.fx21044.entity.User;
import com.fx21044.repositories.ClinicRepository;
import com.fx21044.repositories.DoctorRepository;
import com.fx21044.repositories.SpecializationRepository;
import com.fx21044.repositories.UserRepository;
import com.fx21044.response.MessageResponse;
import com.fx21044.service.ClinicService;
import com.fx21044.service.DoctorService;
import com.fx21044.service.ScheduleService;
import com.fx21044.service.UserDetailsImpl;
import com.fx21044.service.UserService;
import com.fx21044.service.UserServiceImpl;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private SpecializationRepository specializationRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//Lấy danh sách user
	@GetMapping("/users")
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}
	
	//Lấy danh sách doctor
	@GetMapping("/getAllDoctor")
	public List<DoctorDTO> getAllDoctor(){		
		
		return doctorService.getAllDoctor();
	}
	
	//Khóa - Hủy khóa user
	@PutMapping("/lockUser")
	public String lockUser(@RequestParam int userId, @RequestParam int isNotBlocked) {
		
		return userService.lockUser(userId, isNotBlocked);
	}
	
	//Thêm bác sĩ
	@PostMapping("/createDoctor")
	public ResponseEntity<?> registerDoctor(@Valid @RequestBody CreateDoctorDTO createDoctorDTO) {
		
		if (userRepository.existsByEmail(createDoctorDTO.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already in use!"));
	    	}
		
		User user = new User();
		
		user.setName(createDoctorDTO.getName());
		user.setGender(createDoctorDTO.getGender());
		user.setPhone(createDoctorDTO.getPhone());
		user.setEmail(createDoctorDTO.getEmail());
		user.setAddress(createDoctorDTO.getAddress());
		user.setRole("ROLE_DOCTOR");
		user.setIsNotBlocked(1);
		user.setPassword(encoder.encode(createDoctorDTO.getPassword()));
		user.setDescription(createDoctorDTO.getDescription());
		userRepository.save(user);
		
		
		
		Doctor doctor = new Doctor();
		
		doctor.setUser(user);
		Optional<Clinic> clinicOptional = clinicRepository.findById(createDoctorDTO.getClinicId());
		
		if(clinicOptional.isPresent()) {
			Clinic clinic = clinicOptional.get();
			doctor.setClinic(clinic);
		}
		
		Optional<Specialization> specializationOptional = specializationRepository.findById(createDoctorDTO.getSpecializationId());
		
		if(specializationOptional.isPresent()) {
			Specialization specialization = specializationOptional.get();
			doctor.setSpecialization(specialization);
		}
		
		doctorRepository.save(doctor);
		
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	//Xem chi tiết lịch theo user id
	@GetMapping("/getScheduleByUserID")
	public ResponseEntity<?> getScheduleByUserID(@RequestParam("id") int id,@RequestParam("userId") int userId) {
		
		Optional<Schedule> scheduleOptional = scheduleService.getScheduleByIdAndUserID(id, userId);
		
		if(!scheduleOptional.isPresent()) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: This schedules is not existed!"));
		}
		
		Schedule schedule = scheduleOptional.get();
		
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		
		scheduleDTO.setDoctorName(schedule.getDoctor().getUser().getName());
		scheduleDTO.setUserName(schedule.getUser().getName());
		scheduleDTO.setClinicName(schedule.getClinic().getName());
		scheduleDTO.setSpecializationName(schedule.getSpecialization().getName());
		scheduleDTO.setDate(schedule.getDate());
		scheduleDTO.setTime(schedule.getTime());
		scheduleDTO.setDoctorConfirm(schedule.getDoctorConfirm());
		scheduleDTO.setReasonCancel(schedule.getReasonCancel());
		
		return ResponseEntity.ok(scheduleDTO);
	}
	
	//Xem chi tiết lịch theo doctor id
	@GetMapping("/getScheduleByDoctorID")
	public ResponseEntity<?> getScheduleByDoctorID(@RequestParam("id") int id,@RequestParam("doctorId") int doctorId) {
		
		Optional<Schedule> scheduleOptional = scheduleService.getScheduleByIdAndDoctorID(id, doctorId);
		
		if(!scheduleOptional.isPresent()) {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: This schedules is not existed!"));
		}
		
		Schedule schedule = scheduleOptional.get();
		
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		
		scheduleDTO.setDoctorName(schedule.getDoctor().getUser().getName());
		scheduleDTO.setUserName(schedule.getUser().getName());
		scheduleDTO.setClinicName(schedule.getClinic().getName());
		scheduleDTO.setSpecializationName(schedule.getSpecialization().getName());
		scheduleDTO.setDate(schedule.getDate());
		scheduleDTO.setTime(schedule.getTime());
		scheduleDTO.setDoctorConfirm(schedule.getDoctorConfirm());
		scheduleDTO.setReasonCancel(schedule.getReasonCancel());
		
		return ResponseEntity.ok(scheduleDTO);
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
