package com.fx21044.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx21044.entity.Doctor;
import com.fx21044.entity.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	//Tìm bằng tên bác sĩ
	@Query(value = "SELECT * from doctors d inner join users u on d.user_id = u.id  where name like %:name%", nativeQuery = true)
	Doctor findDoctorByName(@Param("name")String name);
	
	//Tìm bằng doctor
	@Query(value = "SELECT * from doctors d inner join users u on d.user_id = u.id where u.id = :id", nativeQuery = true)
	Optional<Doctor> findDoctorByUserId(@Param("id") int id);
}
