package com.fx21044.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fx21044.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer>{
	//Top chuyên khoa
	@Query(value = "SELECT sp.id, sp.name, sp.description, COUNT(*) AS count "
			+ "FROM specializations sp "
			+ "JOIN schedules sc ON sp.id = sc.specialization_id "
			+ "GROUP BY sp.id, sp.name, sp.description "
			+ "ORDER BY count DESC "
			+ "LIMIT 4 ", nativeQuery = true)
	List<Specialization> top4Specializations();
}
