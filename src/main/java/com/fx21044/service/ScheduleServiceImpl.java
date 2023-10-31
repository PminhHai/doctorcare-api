package com.fx21044.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx21044.entity.Schedule;
import com.fx21044.repositories.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	//Lưu lịch khám
	@Override
	public Schedule save(Schedule schedule) {
		
		return scheduleRepository.save(schedule);
	}
	
	//tìm lịch khám bằng doctor id
	@Override
	public List<Schedule> getScheduleByDoctorId(int doctorId) {
		
		return scheduleRepository.getScheduleByDoctorId(doctorId);
	}

	@Override
	public Schedule findById(int id) {
		
		return scheduleRepository.findById(id);
	}
	
	//tìm lịch khám bằng user id
	@Override
	public List<Schedule> getScheduleByUserId(int userId) {
		
		return scheduleRepository.getScheduleByUserId(userId);
	}
	
	//tìm lịch khám bằng id và user id
	@Override
	public Optional<Schedule> getScheduleByIdAndUserID(int id, int userId) {
		
		return scheduleRepository.getScheduleByIdAndUserID(id, userId);
	}
	
	//tìm lịch khám bằng id và doctor id
	@Override
	public Optional<Schedule> getScheduleByIdAndDoctorID(int id, int doctorId) {
		
		return scheduleRepository.getScheduleByIdAndDoctorID(id, doctorId);
	}

}
