package com.ahi.service;

import java.util.Date;
import java.util.List;

import com.ahi.AHCustomException;
import com.ahi.model.ProjectsModel;
import com.ahi.model.TasksModel;
import com.ahi.model.TimesheetModel;

public interface TimesheetService {

	TimesheetModel addTimesheet(TimesheetModel tm) throws AHCustomException;

	TimesheetModel updateTimesheet(TimesheetModel tm) throws AHCustomException;

	List<TimesheetModel> getAllDatas(Integer empId, Date fromDate, Date toDate) throws AHCustomException;

	List<ProjectsModel> getProjectData(Integer empId) throws AHCustomException;

	List<TasksModel> getTaskData(Integer empId) throws AHCustomException;
}
