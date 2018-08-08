package com.ahi.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahi.entity.AhiDepartments;

public interface DepartmentsRepository extends CrudRepository<AhiDepartments, Integer>  {

}
