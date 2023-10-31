package com.fx21044.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx21044.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByToken(String token);
	
	Boolean existsByEmail(String email);
	
	//Tìm user bằng doctor id
	@Query(value = "select u.* from users u "
			+ "join schedules s on u.id = s.user_id where s.doctor_id = :doctorID and s.doctor_confirm = '1'",nativeQuery = true)
	List<User> findUserByDoctorID(@Param("doctorID") int doctorID);
}
