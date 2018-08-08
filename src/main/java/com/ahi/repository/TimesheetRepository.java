package com.ahi.repository;

import org.springframework.data.repository.CrudRepository;
import com.ahi.entity.AhiTimesheet;

public interface TimesheetRepository extends CrudRepository<AhiTimesheet, Integer> {

}

