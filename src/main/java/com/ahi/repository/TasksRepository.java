package com.ahi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ahi.entity.AhiTasks;
import com.ahi.entity.AhiUser;

public interface TasksRepository extends CrudRepository<AhiTasks, Integer> {
	
	List<AhiTasks> findByUser(AhiUser ahiUser);

}
