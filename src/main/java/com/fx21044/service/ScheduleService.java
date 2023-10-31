package com.fx21044.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.fx21044.entity.Schedule;

public interface ScheduleService {
	
	Schedule save(Schedule schedule);
	
	List<Schedule> getScheduleByDoctorId(int doctorId);
	
	Schedule findById(int id);
	
	List<Schedule> getScheduleByUserId(int userId);
	
	Optional<Schedule> getScheduleByIdAndUserID(int id, int userId);
	
	Optional<Schedule> getScheduleByIdAndDoctorID(int id, int doctorId);
}
