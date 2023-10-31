package com.fx21044.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx21044.dto.Pathology;
import com.fx21044.dto.UserDTO;
import com.fx21044.dto.UserDoctorListDTO;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.User;
import com.fx21044.exception.ResourceNotFoundException;
import com.fx21044.repositories.ScheduleRepository;
import com.fx21044.repositories.UserRepository;
import com.fx21044.response.MessageResponse;

@Service
public class UserServiceImpl implements UserService {
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	//Danh sách user
	public List<UserDTO> findAll() {
		
		List<User> users = userRepository.findAll();
		
		List<UserDTO> result = new ArrayList<UserDTO>();
		
		for(int i = 0; i < users.size();i++) {
			UserDTO userDTO = new UserDTO();
			
			userDTO.setId(users.get(i).getId());
			userDTO.setEmail(users.get(i).getEmail());
			userDTO.setName(users.get(i).getName());
			userDTO.setGender(users.get(i).getGender());
			userDTO.setPhone(users.get(i).getPhone());
			userDTO.setAddress(users.get(i).getAddress());
			userDTO.setIsNotBlocked(users.get(i).getIsNotBlocked());
			userDTO.setRole(users.get(i).getRole());
			
			result.add(userDTO);
		}
		
		
		return result;
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public void remove(int id) {
		userRepository.deleteById(id);
	}
	
	//Login
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElse(null);
		if(user == null) {
			throw new ResourceNotFoundException("Email is not existed");
		}
		return UserDetailsImpl.build(user);
	}
	
	//Quên mật khẩu
	@Override
	public String forgotPassword(String email) {
		
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}
		
		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());
		
		user = userRepository.save(user);
		
		return user.getToken();
	}
	
	//Reset mật khẩu
	@Override
	public String resetPassword(String token, String password) {
		
		Optional<User> userOptional = userRepository.findByToken(token);
		
		if(!userOptional.isPresent()) {
			return "Invalid token.";
		}
		
		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();
		
		if(isTokenExpired(tokenCreationDate)) {
			return "Token expired";
		}
		
		User user = userOptional.get();
		
		user.setPassword(passwordEncoder.encode(password));
		user.setToken(null);
		user.setTokenCreationDate(null);
		
		userRepository.save(user);
		
		return "Your password successfully updated.";
	}
	
	//Lấy token reset password
	private String generateToken() {
		StringBuilder token = new StringBuilder();
		
		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	//Hạn token
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
		
		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);
		
		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
	
	//Lấy danh sách user của doctor
	@Override
	@Transactional
	public List<UserDoctorListDTO> findUserByDoctorID(int doctorID) {
		List<User> users = userRepository.findUserByDoctorID(doctorID);
		
		
		
		List<UserDoctorListDTO> result = new ArrayList<>();
		
		for(int i = 0; i < users.size();i++) {
			UserDoctorListDTO userDoctorListDTO = new UserDoctorListDTO();
			userDoctorListDTO.setName(users.get(i).getName());
			userDoctorListDTO.setGender(users.get(i).getGender());
			userDoctorListDTO.setAddress(users.get(i).getAddress());
			
			List<Schedule> schedules = scheduleService.getScheduleByUserId(users.get(i).getId());
			
			
			List<Pathology> pathologies = new ArrayList<>();
			
			for(int j = 0; j < schedules.size(); j++) {
				
				Pathology pathology =  new Pathology();
				pathology.setPathologyName(schedules.get(j).getHealthResult().getPathology());
				pathology.setPathologicalDescription(schedules.get(j).getHealthResult().getPathologicalDescription());
				
				pathologies.add(pathology);
			}
			
			userDoctorListDTO.setPathologies(pathologies);
			
			result.add(userDoctorListDTO);
		}
		
		return result;
	}
	
	//Khóa mở khóa user
	@Override
	public String lockUser(int userId, int isNotBlocked) {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			return "Invalid User ID";
		}
		
		User user = userOptional.get();
		
		user.setIsNotBlocked(isNotBlocked);
		
		userRepository.save(user);
		
		return "Action success";
	}
	
	
}
