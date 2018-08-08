package com.ahi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ahi.entity.AhiUser;

public interface UserRepository extends CrudRepository<AhiUser, Integer>{

	Optional<AhiUser> findByLoginIdIgnoreCase(String userName);
	
	AhiUser getSupervisorBySupervisorId(String supervisorId);
}
