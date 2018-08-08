package com.ahi.service;

import java.util.List;

import com.ahi.AHCustomException;
import com.ahi.model.DepartmentsModel;

public interface DepartmentService {

	DepartmentsModel addDepartment(DepartmentsModel dm) throws AHCustomException;

	boolean deleteDepartment(Integer DepartmentId) throws AHCustomException;

	DepartmentsModel updateDepartment(DepartmentsModel dm) throws AHCustomException;

	List<DepartmentsModel> getAllDepartments() throws AHCustomException;


}
