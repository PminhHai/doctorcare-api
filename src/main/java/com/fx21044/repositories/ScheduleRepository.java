package com.fx21044.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx21044.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	//Tìm lịch khám bằng user id
	@Query(value = "SELECT * FROM schedules s WHERE s.user_id = :userId", nativeQuery = true)
    List<Schedule> findByUserId(@Param("userId") int userId);
	//Tìm lịch khám bằng doctor id
	@Query(value = "SELECT * FROM schedules s WHERE s.doctor_id = :doctorId", nativeQuery = true)
	List<Schedule> getScheduleByDoctorId(@Param("doctorId") int doctorId);
	
	//Tìm bằng id
	Schedule findById(int id);
	
	@Query(value = "SELECT * FROM schedules s WHERE s.user_id = :userId", nativeQuery = true)
	List<Schedule> getScheduleByUserId(@Param("userId") int userId);
	
	//Xem chi tiết lịch khám bệnh nhân
	@Query(value = "SELECT * FROM schedules s WHERE s.id = :id AND s.user_id = :userId", nativeQuery = true)
	Optional<Schedule> getScheduleByIdAndUserID(@Param("id") int id,@Param("userId") int userId);
	//Xem chi tiết lịch khám bằng doctor id
	@Query(value = "SELECT * FROM schedules s WHERE s.id = :id AND s.doctor_id = :doctorId", nativeQuery = true)
	Optional<Schedule> getScheduleByIdAndDoctorID(@Param("id") int id,@Param("doctorId") int doctorId);
}
