package com.fx21044.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fx21044.dto.SignUpDTO;
import com.fx21044.dto.UpdateInfoDTO;
import com.fx21044.dto.DoctorLoginDTO;
import com.fx21044.dto.LoginDTO;
import com.fx21044.dto.PatientDTO;
import com.fx21044.dto.SchedulePatientDTO;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.User;
import com.fx21044.model.JwtResponse;
import com.fx21044.repositories.ScheduleRepository;
import com.fx21044.repositories.UserRepository;
import com.fx21044.response.MessageResponse;
import com.fx21044.service.DoctorService;
import com.fx21044.service.JwtService;
import com.fx21044.service.UserDetailsImpl;
import com.fx21044.service.UserService;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	//Đăng nhập
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO userDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtService.generateTokenLogin(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User currentUser = userService.findByEmail(userDto.getEmail()).get();
		
		if("ROLE_DOCTOR".equals(currentUser.getRole())) {
			Optional<Doctor> docOptional = doctorService.findDoctorByUserId(currentUser.getId());
			
			if(!docOptional.isPresent()) {
				return ResponseEntity
				          .badRequest()
				          .body(new MessageResponse("This is not a doctor"));
			}
			
			Doctor doctor = docOptional.get();
			DoctorLoginDTO doctorLoginDTO = new DoctorLoginDTO(jwt, currentUser.getId(), currentUser.getName(), currentUser.getEmail(),currentUser.getRole());
			doctorLoginDTO.setClinicName(doctor.getSpecialization().getName());
			doctorLoginDTO.setSpecializationName(doctor.getSpecialization().getName());
			
			return ResponseEntity.ok(doctorLoginDTO);
		} else if ("ROLE_USER".equals(currentUser.getRole())) {
			PatientDTO patientDTO = new PatientDTO(jwt, currentUser.getId(), currentUser.getName(), currentUser.getEmail(),currentUser.getRole());
			
			List<Schedule> schedules = scheduleRepository.findByUserId(currentUser.getId());
			
			List<SchedulePatientDTO> schedulePatientDTOs = new ArrayList<SchedulePatientDTO>();
			
			
			for(int i = 0; i < schedules.size(); i++) {
				SchedulePatientDTO schedulePatientDTO = new SchedulePatientDTO();
				schedulePatientDTO.setScheduleId(schedules.get(i).getId());
				schedulePatientDTO.setDoctorName(schedules.get(i).getDoctor().getUser().getName());
				schedulePatientDTO.setClinicName(schedules.get(i).getClinic().getName());
				schedulePatientDTO.setSpecializationName(schedules.get(i).getSpecialization().getName());
				schedulePatientDTO.setDate(schedules.get(i).getDate());
				schedulePatientDTO.setTime(schedules.get(i).getTime());
				
				schedulePatientDTOs.add(schedulePatientDTO);
			}
			
			patientDTO.setSchedulePatientDTOs(schedulePatientDTOs);
			
			return ResponseEntity.ok(patientDTO);
		}
		
		return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getName(), currentUser.getEmail(),currentUser.getRole()));
	}
	
	//Đăng ký
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpDTO) {
		
		if (userRepository.existsByEmail(signUpDTO.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already in use!"));
	    	}
		 
		User user = new User();
	
		user.setName(signUpDTO.getName());
		user.setGender(signUpDTO.getGender());
		user.setPhone(signUpDTO.getPhone());
		user.setEmail(signUpDTO.getEmail());
		user.setAddress(signUpDTO.getAddress());
		user.setRole(signUpDTO.getRole());
		user.setIsNotBlocked(1);
		user.setPassword(encoder.encode(signUpDTO.getPassword()));
		
		
		
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	//Quên mật khẩu
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email) {
		
		String response = userService.forgotPassword(email);
		
		if (!response.startsWith("Invalid")) {
			response = "http://localhost:8080/reset-password?token=" + response;
		}
		return response;
	}
	
	//Reset mật khẩu
	@PutMapping("/reset-password")
	public String resetPassword(@RequestParam String token, @RequestParam String password) {
		
		return userService.resetPassword(token, password);
	}
	
	
}
