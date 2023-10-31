package com.fx21044.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fx21044.dto.UserDoctorListDTO;
import com.fx21044.entity.Doctor;
import com.fx21044.entity.Schedule;
import com.fx21044.entity.User;

public interface UserService extends UserDetailsService{
	
	Optional<User> findByEmail(String email);
	
	String forgotPassword(String email);
	
	String resetPassword(String token, String password);
	
	List<UserDoctorListDTO> findUserByDoctorID(int doctorID);
	
	String lockUser(int userId, int isNotBlocked);
}
