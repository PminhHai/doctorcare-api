package com.fx21044.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fx21044.entity.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer>{
	
	//Top phòng khám
	@Query(value = "SELECT c.id, c.name, c.address, c.phone, c.work_time, COUNT(*) AS count "
			+ "FROM clinics c "
			+ "JOIN schedules sc ON c.id = sc.clinic_id "
			+ "GROUP BY c.id, c.name, c.address, c.phone, c.work_time "
			+ "ORDER BY count DESC "
			+ "LIMIT 4", nativeQuery = true)
	List<Clinic> top4Clinic();
	
	//Tìm kiếm chung
	@Query(value = "SELECT DISTINCT c.* "
			+ "FROM clinics c INNER JOIN clinic_specialties cs ON c.id = cs.clinic_id "
			+ "INNER JOIN specializations s ON cs.specialization_id = s.id "
			+ "WHERE c.address LIKE %:query% "
			+ "or c.name LIKE %:query% "
			+ "or (s.name LIKE %:query% "
			+ "OR s.description LIKE %:query%)", nativeQuery = true)
	List<Clinic> searchClinics(@Param("query") String query);
	
	//Tìm kiếm bằng chuyên khoa
	@Query(value = "SELECT distinct c.* "
			+ "FROM clinics c "
			+ "JOIN clinic_specialties cs ON c.id = cs.clinic_id "
			+ "JOIN specializations s ON cs.specialization_id = s.id "
			+ "WHERE s.name like %:query%;" , nativeQuery = true)
	List<Clinic> searchClinicsBySpecialization(@Param("query") String query);
}
