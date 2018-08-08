package com.ahi.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahi.entity.AhiProjects;

public interface ProjectsRepository  extends CrudRepository<AhiProjects, Integer>  {

}
