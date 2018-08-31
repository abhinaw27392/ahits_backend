package com.ahi.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import com.ahi.entity.AhiTimesheet;

public interface TimesheetRepository extends CrudRepository<AhiTimesheet, Integer> {

	Iterable<AhiTimesheet> findAllByempIdAndDateBetween(Integer empId, Date fromDate, Date toDate);

}

