package com.ahi.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahi.AHCustomException;
import com.ahi.entity.AhiProjects;
import com.ahi.entity.AhiTasks;
import com.ahi.entity.AhiTimesheet;
import com.ahi.entity.AhiUser;
import com.ahi.model.ProjectsModel;
import com.ahi.model.TasksModel;
import com.ahi.model.TimesheetModel;
import com.ahi.repository.ProjectsRepository;
import com.ahi.repository.TasksRepository;
import com.ahi.repository.TimesheetRepository;
import com.ahi.repository.UserRepository;
import com.ahi.service.TimesheetService;

@Service
public class TimesheetServiceImpl implements TimesheetService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Autowired
	private UserRepository ahiUserRepository;
	
	@Autowired
	private ProjectsRepository projectsRepository;
	
	@Autowired
	private TasksRepository tasksRepository;

	@Override
	public TimesheetModel addTimesheet(TimesheetModel tm) throws AHCustomException {
		try {
			AhiTimesheet ts = new AhiTimesheet();
			ts.setProjectName(tm.getProjectName());
			ts.setTaskName(tm.getTaskName());
			ts.setDate(tm.getDate());
			ts.setTotalHours(tm.getTotalHours());
			ts.setEmpId(ahiUserRepository.findById(tm.getEmpId()).get().getId());
			timesheetRepository.save(ts);
			tm.setId(ts.getId());
			return tm;
		} catch (Exception e) {
			log.error("Error while adding task" +  e);
			throw new AHCustomException("Error while adding task");
		}
	}

	@Override
	public TimesheetModel updateTimesheet(TimesheetModel tm) throws AHCustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimesheetModel> getAllDatas(Integer empId, Date fromDate, Date toDate) throws AHCustomException {
		try  {
			List<TimesheetModel> models = new ArrayList<>();
			Iterable<AhiTimesheet> timesheet = timesheetRepository.findAllByempIdAndDateBetween(empId, fromDate, toDate);
			Iterator<AhiTimesheet> iterator = timesheet.iterator();
			while(iterator.hasNext()) {
				TimesheetModel tsm = new TimesheetModel();
				AhiTimesheet ts = iterator.next();
				tsm.setId(ts.getId());
				tsm.setEmpId(ts.getEmpId());
				tsm.setProjectName(ts.getProjectName());
				tsm.setTaskName(ts.getTaskName());
				tsm.setDate(ts.getDate());
				tsm.setTotalHours(ts.getTotalHours());
				models.add(tsm);
			}
			return models;
		}catch(Exception e) {
			log.error("Error while getting AllDatas");
			throw new AHCustomException("Error while getting AllDatas");
		}
	}
	
	@Override
	public List<ProjectsModel> getProjectData(Integer empId) throws AHCustomException {
		try {
			List<ProjectsModel> models = new ArrayList<>();
			Iterable<AhiProjects> projects =projectsRepository.findAll();
			Iterator<AhiProjects> iterator = projects.iterator();
			while (iterator.hasNext()) {
				ProjectsModel pm = new ProjectsModel();
				AhiProjects project = iterator.next();
				pm.setProjectId(project.getProjectId());
				pm.setProjectName(project.getProjectName());
				models.add(pm);
			}
			return models;
		} catch (Exception e) {
			log.error("Error while getting ProjectDatas");
			throw new AHCustomException("Error while getting ProjectDatas");
		}
	}
	
	@Override
	public List<TasksModel> getTaskData(Integer empId) throws AHCustomException {
		try {
			List<TasksModel> models = new ArrayList<>();
			Iterable<AhiTasks> tasks =tasksRepository.findAll();
			Iterator<AhiTasks> iterator = tasks.iterator();
			while (iterator.hasNext()) {
				TasksModel tm = new TasksModel();
				AhiTasks task = iterator.next();
				tm.setTaskId(task.getTaskId());
				tm.setTaskName(task.getTaskName());
				models.add(tm);
			}
			return models;
		} catch (Exception e) {
			log.error("Error while getting TaskDatas");
			throw new AHCustomException("Error while getting TaskDatas");
		}
	}

}

